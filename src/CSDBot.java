
import command.Command;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static util.Constants.*;

/**
 * This class use static fields from util.Constants
 */
public class CSDBot extends TelegramLongPollingBot {
    private final ReplyKeyboardMarkup mainKeyboard;
    private final Command command;

    public CSDBot(Command command) {
        this.command = command;
        mainKeyboard = createMainKeyboard();
    }

    private ReplyKeyboardMarkup createMainKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(FIRST_COURSE);
        row.add(SECOND_COURSE);
        row.add(THIRD_COURSE);
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add(FOURTH_COURSE);
        row.add(FIFTH_COURSE);
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add(SCHEDULE_OF_BELL);
        row.add(SCHEDULE_OF_WEEKS);
        keyboardRows.add(row);

        keyboard.setKeyboard(keyboardRows);
        return keyboard;
    }

    @Override
    public String getBotToken() {
        return Constants.TOKEN_OF_BOT;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String firstName = update.getMessage().getChat().getFirstName();
        String lastName = update.getMessage().getChat().getLastName();
        String username = update.getMessage().getChat().getUserName();

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());

            String text = update.getMessage().getText();

            if (text.equals(START_CMD)) {
                logging(firstName, lastName, username, text, GREETING_MESSAGE);
                sendResponse(sendMessage);
            } else {
                logging(firstName, lastName, username, text, "sending photo");
                sendResponse(text, update.getMessage().getChatId());
            }
        }
    }

    private void sendResponse(String text, Long chatId) {
        List<String> response = command.execute(text);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);

        try {
            for (String s : response) {
                System.out.println(s);
                sendPhoto.setPhoto(s);
                sendPhoto(sendPhoto);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(SendMessage sendMessage) {
        sendMessage.setText(GREETING_MESSAGE);
        sendMessage.setReplyMarkup(mainKeyboard);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void logging(String firstName, String lastName, String userId, String txt, String botAnswer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + firstName + " " + lastName + ". (id = " + userId + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + botAnswer);
    }

    @Override
    public String getBotUsername() {
        return Constants.NAME_OF_BOT;
    }
}
