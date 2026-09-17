package uz.pdp.classTask;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String botToken = "8978927499:AAHn9QT8v5M_wUhzBm8t_c8cT5qb0RO6mio";
        TelegramBot bot = new TelegramBot(botToken);
        SendMessage sendMessage = new SendMessage("6276983680", "Salom");
        bot.execute(sendMessage);

       /* SendPhoto sendPhoto = new SendPhoto("6276983680",
                Files.readAllBytes(Path.of("/Users/bunyodomonov/Documents/terminal kodlar.png")));
        bot.execute(sendPhoto);
*/

        /*ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                new KeyboardButton("📞 Contact us").requestContact(true)},
                new KeyboardButton[]{
                new KeyboardButton("📍 Location").requestLocation(true)},
                new KeyboardButton[]{
                new KeyboardButton("⚙️ Settings")}
        );
        replyKeyboard.resizeKeyboard(true);
        SendMessage sendMessage = new SendMessage("6276983680", "a");
        sendMessage.replyMarkup(replyKeyboard);
        bot.execute(sendMessage);*/


        bot.setUpdatesListener(list -> {
            for (Update update : list) {
                Message message = update.message();
                String text = message.text();
                String firstName = message.chat().firstName();
                System.out.println(firstName + " " + text);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            System.out.println(e.getMessage());
        });
    }
}