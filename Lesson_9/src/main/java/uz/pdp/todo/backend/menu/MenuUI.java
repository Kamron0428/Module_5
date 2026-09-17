package uz.pdp.todo.backend.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.todo.backend.enums.Category;
import uz.pdp.todo.backend.enums.Priority;
import uz.pdp.todo.backend.modules.Task;
import uz.pdp.todo.backend.service.TodoService;

import java.util.List;

public class MenuUI {

    public static void showMainMenu(TelegramBot bot, Long chatId) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("➕ Add Task"),
                        new KeyboardButton("📋 All Tasks")
                },
                new KeyboardButton[]{
                        new KeyboardButton("✅ Complete Task"),
                        new KeyboardButton("🗑 Delete Task")
                }
        ).resizeKeyboard(true);

        bot.execute(new SendMessage(chatId, "Asosiy menyu. Kerakli amalni tanlang:")
                .replyMarkup(keyboard));
    }

    public static void getAllTasks(TelegramBot bot, Long chatId, TodoService todoService) {
        List<Task> tasks = todoService.getAll();

        if (tasks.isEmpty()) {
            bot.execute(new SendMessage(chatId, "📭 Hozircha hech qanday vazifa mavjud emas!"));
            return;
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        for (Task task : tasks) {
            String status = task.isCompleted() ? "✅" : "⏳";
            String title = task.getTitle() != null ? task.getTitle() : "Nomsiz";
            String buttonText = "%s [%d] %s".formatted(status, task.getId(), title);
            markup.addRow(new InlineKeyboardButton(buttonText).callbackData("VIEW_" + task.getId()));
        }

        bot.execute(new SendMessage(chatId, "📋 Barcha vazifalar ro'yxati (batafsil ko'rish uchun bosing):")
                .replyMarkup(markup));
    }

    public static void showTaskDetails(TelegramBot bot, Long chatId, Task task) {
        if (task == null) {
            bot.execute(new SendMessage(chatId, "❌ Vazifa topilmadi!"));
            return;
        }

        String details = """
                📌 Vazifa ma'lumotlari:
                -----------------------------
                🆔 ID: %d
                📝 Sarlavha: %s
                📄 Tavsif: %s
                ⚡️ Muhimlik: %s
                🏷 Kategoriya: %s
                📊 Holat: %s
                """.formatted(
                task.getId() != null ? task.getId() : 0L,
                task.getTitle() != null ? task.getTitle() : "-",
                task.getDescription() != null ? task.getDescription() : "-",
                task.getPriority() != null ? task.getPriority() : "-",
                task.getCategory() != null ? task.getCategory() : "-",
                task.isCompleted() ? "Bajarilgan ✅" : "Jarayonda ⏳"
        );

        InlineKeyboardMarkup actionMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("✅ Bajarildi deb belgilash")
                                .callbackData("COMPLETE_" + task.getId()),
                        new InlineKeyboardButton("🗑 O'chirish")
                                .callbackData("DELETE_" + task.getId())
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("⬅️ Barcha vazifalar")
                                .callbackData("LIST_TASKS")
                }
        );

        bot.execute(new SendMessage(chatId, details).replyMarkup(actionMarkup));
    }

    public static void showPriorityMenu(TelegramBot bot, Long chatId) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton(Priority.LOW.name()),
                new KeyboardButton(Priority.MEDIUM.name()),
                new KeyboardButton(Priority.HIGH.name()))
                .resizeKeyboard(true).oneTimeKeyboard(true);

        bot.execute(new SendMessage(chatId, "⚡️ Vazifa muhimligini tanlang:").replyMarkup(keyboard));
    }

    public static void showCategoryMenu(TelegramBot bot, Long chatId) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton(Category.STUDY.name()),
                new KeyboardButton(Category.WORK.name()),
                new KeyboardButton(Category.ENTERTAINMENT.name()))
                .resizeKeyboard(true).oneTimeKeyboard(true);

        bot.execute(new SendMessage(chatId, "🏷️ Vazifa kategoriyasini tanlang:").replyMarkup(keyboard));
    }
}
