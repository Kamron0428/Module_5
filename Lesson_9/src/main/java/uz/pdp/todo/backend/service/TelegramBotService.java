package uz.pdp.todo.backend.service;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.model.*;
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

        if (text.equals("/start")) {
            userStates.remove(chatId);
            userTasks.remove(chatId);
            bot.execute(new SendMessage(chatId, "Assalomu alaykum! ToDo Botga xush kelibsiz."));
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
                MenuUI.getAllTasks(bot, chatId, todoService);
                return;
            }
            case "✅ Complete Task" -> {
                userStates.put(chatId, UserState.AWAITING_COMPLETE_ID);
                bot.execute(new SendMessage(chatId, "Yakunlamoqchi bo'lgan vazifangizning ID raqamini kiriting:"));
                return;
            }
            case "🗑 Delete Task" -> {
                userStates.put(chatId, UserState.AWAITING_DELETE_ID);
                bot.execute(new SendMessage(chatId, "O'chirmoqchi bo'lgan vazifangizning ID raqamini kiriting:"));
                return;
            }
        }

        UserState state = userStates.get(chatId);
        if (state != null) {
            processState(chatId, text, state);
        }
    }

    private void processState(Long chatId, String text, UserState state) {
        switch (state) {
            case AWAITING_TITLE -> {
                if (text.length() > 20) {
                    bot.execute(new SendMessage(chatId, "⚠️ Sarlavha 20 belgidan oshmasligi kerak! Qaytadan kiriting:"));
                    return;
                }
                userTasks.get(chatId).setTitle(text);
                userStates.put(chatId, UserState.AWAITING_DESCRIPTION);
                bot.execute(new SendMessage(chatId, "Vazifa tavsifini (description) kiriting:"));
            }
            case AWAITING_DESCRIPTION -> {
                userTasks.get(chatId).setDescription(text);
                userStates.put(chatId, UserState.AWAITING_PRIORITY);
                MenuUI.showPriorityMenu(bot, chatId);
            }
            case AWAITING_PRIORITY -> {
                try {
                    userTasks.get(chatId).setPriority(Priority.valueOf(text));
                    userStates.put(chatId, UserState.AWAITING_CATEGORY);
                    MenuUI.showCategoryMenu(bot, chatId);
                } catch (IllegalArgumentException e) {
                    bot.execute(new SendMessage(chatId, "Iltimos, tugmalardan birini tanlang!"));
                }
            }
            case AWAITING_CATEGORY -> {
                try {
                    Task task = userTasks.get(chatId);
                    task.setCategory(Category.valueOf(text));

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
                    bot.execute(new SendMessage(chatId, "Iltimos, tugmalardan birini tanlang!"));
                }
            }
            case AWAITING_COMPLETE_ID -> {
                try {
                    Long id = Long.parseLong(text);
                    todoService.complete(id);
                    userStates.remove(chatId);
                    bot.execute(new SendMessage(chatId, "✅ " + id + "-raqamli vazifa yakunlandi deb belgilandi!"));
                    MenuUI.showMainMenu(bot, chatId);
                } catch (NumberFormatException e) {
                    bot.execute(new SendMessage(chatId, "Xatolik: Faqat raqam kiriting!"));
                }
            }
            case AWAITING_DELETE_ID -> {
                try {
                    Long id = Long.parseLong(text);
                    todoService.delete(id);
                    userStates.remove(chatId);
                    bot.execute(new SendMessage(chatId, "🗑 " + id + "-raqamli vazifa o'chirildi!"));
                    MenuUI.showMainMenu(bot, chatId);
                } catch (NumberFormatException e) {
                    bot.execute(new SendMessage(chatId, "Xatolik: Faqat raqam kiriting!"));
                }
            }
        }
    }

    private void handleCallback(CallbackQuery callback) {
        Long chatId = callback.message().chat().id();
        String data = callback.data();

        if (data.startsWith("VIEW_")) {
            Long taskId = Long.parseLong(data.substring("VIEW_".length()));
            Task task = todoService.getTaskById(taskId);
            MenuUI.showTaskDetails(bot, chatId, task);
        } else if (data.startsWith("COMPLETE_")) {
            Long taskId = Long.parseLong(data.substring("COMPLETE_".length()));
            todoService.complete(taskId);
            bot.execute(new SendMessage(chatId, "✅ Vazifa yakunlandi!"));
            MenuUI.getAllTasks(bot, chatId, todoService);
        } else if (data.startsWith("DELETE_")) {
            Long taskId = Long.parseLong(data.substring("DELETE_".length()));
            todoService.delete(taskId);
            bot.execute(new SendMessage(chatId, "🗑 Vazifa o'chirildi!"));
            MenuUI.getAllTasks(bot, chatId, todoService);
        }
    }
}
