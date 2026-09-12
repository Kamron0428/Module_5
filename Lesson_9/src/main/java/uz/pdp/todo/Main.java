package uz.pdp.todo;

import uz.pdp.todo.backend.service.TelegramBotService;

public class Main {
    public static void main(String[] args) {

        String token = "8978927499:AAHn9QT8v5M_wUhzBm8t_c8cT5qb0RO6mio";
        TelegramBotService telegramBotService = new TelegramBotService(token);
        telegramBotService.start();
    }
}
