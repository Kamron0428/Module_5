package uz.pdp.todo.backend.service;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.todo.backend.dtos.TaskCreateDTO;
import uz.pdp.todo.backend.enums.*;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.menu.MenuUI;

import java.util.HashMap;
import java.util.Map;

public class TelegramBotService {

    private final TelegramBot bot;
    private final TodoService todoService;

    private final Map<Long, UserState> userStates = new HashMap<>();
    private final Map<Long, Task> userTasks = new HashMap<>();

    public TelegramBotService(String token) {
        this.bot = new TelegramBot(token);
        this.todoService = new TodoServiceImpl();
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    if (update.message() != null) {
                        handleMessage(update.message());
                    } else if (update.callbackQuery() != null) {
                        handleCallback(update.callbackQuery());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        System.out.println("🤖 Bot muvaffaqiyatli ishga tushdi!");
    }

    private void handleMessage(Message message) {
        Long chatId = message.chat().id();
        String text = message.text();

        if (text == null) return;

        if (text.equals("/start") || text.equalsIgnoreCase("/cancel") || text.equals("❌ Bekor qilish")) {
            userStates.remove(chatId);
            userTasks.remove(chatId);
            if (text.equals("/start")) {
                bot.execute(new SendMessage(chatId, "Assalomu alaykum! ToDo Botga xush kelibsiz."));
            } else {
                bot.execute(new SendMessage(chatId, "Amal bekor qilindi."));
            }
            MenuUI.showMainMenu(bot, chatId);
            return;
        }

        switch (text) {
            case "➕ Add Task" -> {
                userStates.put(chatId, UserState.AWAITING_TITLE);
                userTasks.put(chatId, new Task());
                bot.execute(new SendMessage(chatId, "Yangi vazifa sarlavhasini kiriting (20 belgidan oshmasin):"));
                return;
            }
            case "📋 All Tasks" -> {
                userStates.remove(chatId);
                userTasks.remove(chatId);
                MenuUI.getAllTasks(bot, chatId, todoService);
                return;
            }
            case "✅ Complete Task" -> {
                userTasks.remove(chatId);
                userStates.put(chatId, UserState.AWAITING_COMPLETE_ID);
                bot.execute(new SendMessage(chatId, "Yakunlamoqchi bo'lgan vazifangizning ID raqamini kiriting:"));
                return;
            }
            case "🗑 Delete Task" -> {
                userTasks.remove(chatId);
                userStates.put(chatId, UserState.AWAITING_DELETE_ID);
                bot.execute(new SendMessage(chatId, "O'chirmoqchi bo'lgan vazifangizning ID raqamini kiriting:"));
                return;
            }
        }

        UserState state = userStates.get(chatId);
        if (state != null) {
            processState(chatId, text.trim(), state);
        }
    }

    private void processState(Long chatId, String text, UserState state) {
        switch (state) {
            case AWAITING_TITLE -> {
                if (text.length() > 20) {
                    bot.execute(new SendMessage(chatId, "⚠️ Sarlavha 20 belgidan oshmasligi kerak! Qaytadan kiriting:"));
                    return;
                }
                userTasks.computeIfAbsent(chatId, k -> new Task()).setTitle(text);
                userStates.put(chatId, UserState.AWAITING_DESCRIPTION);
                bot.execute(new SendMessage(chatId, "Vazifa tavsifini (description) kiriting:"));
            }
            case AWAITING_DESCRIPTION -> {
                userTasks.computeIfAbsent(chatId, k -> new Task()).setDescription(text);
                userStates.put(chatId, UserState.AWAITING_PRIORITY);
                MenuUI.showPriorityMenu(bot, chatId);
            }
            case AWAITING_PRIORITY -> {
                try {
                    Priority priority = Priority.valueOf(text.toUpperCase());
                    userTasks.computeIfAbsent(chatId, k -> new Task()).setPriority(priority);
                    userStates.put(chatId, UserState.AWAITING_CATEGORY);
                    MenuUI.showCategoryMenu(bot, chatId);
                } catch (IllegalArgumentException e) {
                    bot.execute(new SendMessage(chatId, "⚠️ Iltimos, pastdagi tugmalardan birini tanlang!"));
                    MenuUI.showPriorityMenu(bot, chatId);
                }
            }
            case AWAITING_CATEGORY -> {
                try {
                    Category category = Category.valueOf(text.toUpperCase());
                    Task task = userTasks.get(chatId);
                    if (task == null) {
                        userStates.remove(chatId);
                        bot.execute(new SendMessage(chatId, "❌ Xatolik yuz berdi. Iltimos, qaytadan boshlang."));
                        MenuUI.showMainMenu(bot, chatId);
                        return;
                    }
                    task.setCategory(category);

                    TaskCreateDTO dto = new TaskCreateDTO(
                            task.getTitle(),
                            task.getDescription(),
                            task.getPriority(),
                            task.getCategory(),
                            false
                    );
                    Task createdTask = todoService.create(dto);

                    userStates.remove(chatId);
                    userTasks.remove(chatId);

                    bot.execute(new SendMessage(chatId, "🎉 Vazifa muvaffaqiyatli saqlandi! (ID: " + createdTask.getId() + ")"));
                    MenuUI.showMainMenu(bot, chatId);
                } catch (IllegalArgumentException e) {
                    bot.execute(new SendMessage(chatId, "⚠️ Iltimos, pastdagi tugmalardan birini tanlang!"));
                    MenuUI.showCategoryMenu(bot, chatId);
                }
            }
            case AWAITING_COMPLETE_ID -> {
                try {
                    Long id = Long.parseLong(text);
                    boolean completed = todoService.complete(id);
                    userStates.remove(chatId);
                    if (completed) {
                        bot.execute(new SendMessage(chatId, "✅ " + id + "-raqamli vazifa yakunlandi deb belgilandi!"));
                    } else {
                        bot.execute(new SendMessage(chatId, "❌ " + id + "-raqamli vazifa topilmadi!"));
                    }
                    MenuUI.showMainMenu(bot, chatId);
                } catch (NumberFormatException e) {
                    bot.execute(new SendMessage(chatId, "⚠️ Xatolik: Faqat raqam kiriting (bekor qilish uchun /cancel):"));
                }
            }
            case AWAITING_DELETE_ID -> {
                try {
                    Long id = Long.parseLong(text);
                    boolean deleted = todoService.delete(id);
                    userStates.remove(chatId);
                    if (deleted) {
                        bot.execute(new SendMessage(chatId, "🗑 " + id + "-raqamli vazifa o'chirildi!"));
                    } else {
                        bot.execute(new SendMessage(chatId, "❌ " + id + "-raqamli vazifa topilmadi!"));
                    }
                    MenuUI.showMainMenu(bot, chatId);
                } catch (NumberFormatException e) {
                    bot.execute(new SendMessage(chatId, "⚠️ Xatolik: Faqat raqam kiriting (bekor qilish uchun /cancel):"));
                }
            }
        }
    }

    private void handleCallback(CallbackQuery callback) {
        Long chatId = callback.message() != null ? callback.message().chat().id() : callback.from().id();
        String data = callback.data();

        // Standard Telegram Bot API requirement: always answer callback queries to stop the loading animation
        bot.execute(new AnswerCallbackQuery(callback.id()));

        if (data.equals("LIST_TASKS")) {
            MenuUI.getAllTasks(bot, chatId, todoService);
        } else if (data.startsWith("VIEW_")) {
            try {
                Long taskId = Long.parseLong(data.substring("VIEW_".length()));
                Task task = todoService.getTaskById(taskId);
                MenuUI.showTaskDetails(bot, chatId, task);
            } catch (NumberFormatException e) {
                bot.execute(new SendMessage(chatId, "❌ Noto'g'ri vazifa ID si!"));
            }
        } else if (data.startsWith("COMPLETE_")) {
            try {
                Long taskId = Long.parseLong(data.substring("COMPLETE_".length()));
                boolean success = todoService.complete(taskId);
                if (success) {
                    bot.execute(new SendMessage(chatId, "✅ Vazifa yakunlandi!"));
                } else {
                    bot.execute(new SendMessage(chatId, "❌ Vazifa topilmadi!"));
                }
                MenuUI.getAllTasks(bot, chatId, todoService);
            } catch (NumberFormatException e) {
                bot.execute(new SendMessage(chatId, "❌ Noto'g'ri vazifa ID si!"));
            }
        } else if (data.startsWith("DELETE_")) {
            try {
                Long taskId = Long.parseLong(data.substring("DELETE_".length()));
                boolean success = todoService.delete(taskId);
                if (success) {
                    bot.execute(new SendMessage(chatId, "🗑 Vazifa o'chirildi!"));
                } else {
                    bot.execute(new SendMessage(chatId, "❌ Vazifa topilmadi!"));
                }
                MenuUI.getAllTasks(bot, chatId, todoService);
            } catch (NumberFormatException e) {
                bot.execute(new SendMessage(chatId, "❌ Noto'g'ri vazifa ID si!"));
            }
        }
    }
}

