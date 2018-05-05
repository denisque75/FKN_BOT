import command.Command;
import command.Executor;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Command command = new Executor();
        try {
            botsApi.registerBot(new CSDBot(command));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
