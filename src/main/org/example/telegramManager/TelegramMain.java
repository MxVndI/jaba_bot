package main.org.example.telegramManager;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

import main.org.example.gameLogic.Jaba;
import main.org.example.gameLogic.RandomizerMap;
import main.org.example.gameLogic.Render;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramMain extends MultiSessionTelegramBot {
    RandomizerMap randomizerMap = new RandomizerMap();
    private Jaba jab;
    private Render render;
    private DatabaseManager databaseManager = new DatabaseManager();
    private boolean isMenu = false;
    private boolean isNickname = false;

    public static final String NAME = "JabaGame"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = ""; //TODO: добавьте токен бота в кавычках

    public TelegramMain() throws FileNotFoundException {
        super(NAME, TOKEN);
    }

    boolean startWait = false;


    @Override
    public void onUpdateEventReceived(Update updateEvent) throws FileNotFoundException {
        String nickname = "";
        if (updateEvent.hasMessage() && updateEvent.getMessage().hasText() && startWait == false) { // Переменная = false значит идет "запрос" на продолжение
            String command = updateEvent.getMessage().getText();

            if (getMessageText().equals("/start")) {
                startWait = true;
                sendTextMessageAsync("Введите ваш никнейм");
            }
        } else if (updateEvent.hasMessage() && updateEvent.getMessage().hasText() && startWait == true) {
            startWait = false;
            nickname = updateEvent.getMessage().getText();
            try {
                databaseManager.addNewPlayer(nickname);
            } catch (SQLException e) {}
            sendTextMessageAsync(Messages.HELLO_TEXT);
        }
        if (getMessageText().equals("меню")) {
            isMenu = true;
            sendTextMessageAsync("Выберите действие",
                    Map.of("Играть", "play",
                            "Баланс кристаллов", "balance",
                            "Топ игроков", "top"));
        }
        if (getCallbackQueryButtonKey().equals("play")) {
            startGame();
            sendMapText();
        }
        if (getCallbackQueryButtonKey().equals("balance")) {
            try {
                sendTextMessageAsync("У вас " + databaseManager.getPlayerGems() + "💎");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (getCallbackQueryButtonKey().equals("top")) {
            try {
                sendTextMessageAsync("Топ игроков \n" + databaseManager.getTop());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (getMessageText().equals("начать новую игру")) {
            startGame();
            sendMapText();
        }
        if (jab.getChecker().isWin() != true && jab.getChecker().isLose() != true && !isMenu) {
            if (getCallbackQueryButtonKey().equals("up")) {
                jab.moving("w");
            }
            if (getCallbackQueryButtonKey().equals("left")) {
                jab.moving("a");
            }
            if (getCallbackQueryButtonKey().equals("down")) {
                jab.moving("s");
            }
            if (getCallbackQueryButtonKey().equals("right")) {
                jab.moving("d");
            }
            if (getCallbackQueryButtonKey().equals("cut")) {
                jab.moving("cut");
            }
            if (jab.getHaveAxe()) {
                editTextMessageAsync(getLastSentMessage().getMessageId(), "Количество ходов: " + jab.getMoveCount() +
                                "\nКоличество кристаллов: " + jab.getCountGems() +
                                "<pre><code>" + jab.getRender().render(jab.getGameMap().getGameMapArray()) + "</code></pre>",
                        Map.of("⬆️", "up",
                                "⬅️", "left",
                                "⬇️", "down",
                                "➡️", "right",
                                "🪓", "cut"));
            } else if (!isMenu) {
                editTextMessageAsync(getLastSentMessage().getMessageId(), "Количество ходов: " + jab.getMoveCount() +
                                "\nКоличество кристаллов: " + jab.getCountGems() +
                                "<pre><code>" + jab.getRender().render(jab.getGameMap().getGameMapArray()) + "</code></pre>",
                        Map.of("⬆️", "up",
                                "⬅️", "left",
                                "⬇️", "down",
                                "➡️", "right"));
                if (jab.getChecker().isWin() == true) {
                    try {
                        databaseManager.updatePlayerData();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    sendTextMessageAsync(Messages.WIN_TEXT);
                }
                if (jab.getChecker().isLose() == true) {
                    sendTextMessageAsync(Messages.LOSE_TEXT);
                }
            }
        }

    }

    public static void main(String[] args) throws TelegramApiException, FileNotFoundException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramMain());
    }

    private void sendMapText() {
        sendTextMessageAsync("Количество ходов: " + jab.getMoveCount() +
                        "\nКоличество кристаллов: " + jab.getCountGems() +
                        "<pre><code>" + jab.getRender().render(jab.getGameMap().getGameMapArray()) + "</code></pre>",
                Map.of("⬆️", "up",
                        "⬅️", "left",
                        "⬇️", "down",
                        "➡️", "right"));
    }

    private void startGame() throws FileNotFoundException {
        isMenu = false;
        jab = new Jaba();
        render = jab.getRender();
    }

    class DatabaseManager {
        private final String DB_USERNAME = "";
        private final String DB_PASSWORD = "";
        private final String DB_URL = "";

        private void addNewPlayer(String nickname) throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String SQL_INSERT_TASK = "insert into \"Player\" values('" + getCurrentChatId() + "', '" + 0 + "', '" + nickname + "');";
            statement.executeQuery(SQL_INSERT_TASK);
            connection.close();
        }

        private void updatePlayerData() throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String SQL_UPDATE_TASK = "update \"Player\" set \"GemsCount\" = ('" + (getPlayerGems(connection) +
                    jab.getCountGems()) + "') where \"PlayerID\" = '" + getCurrentChatId() + "';";
            statement.executeQuery(SQL_UPDATE_TASK);
            connection.close();
        }

        private int getPlayerGems(Connection connection) throws SQLException {
            Statement statement = connection.createStatement();
            String SQL_SELECT_TASK = "select \"GemsCount\" from \"Player\" where \"PlayerID\" = '"
                    + getCurrentChatId() + "';";
            ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
            if (result.next()) {
                int count = result.getInt("GemsCount");
                return count;
            } else return -1;
        }

        private String getTop() throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String SQL_SELECT_TASK = "select \"GemsCount\", \"nickname\" from \"Player\" order by \"GemsCount\" desc limit 5 ;";
            ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
            String answer = "";
            while (result.next()) {
                answer += result.getString("nickname") + " " + result.getInt("GemsCount") + "💎 \n";
            }
            System.out.println(answer);
            return answer;
        }

        private int getPlayerGems() throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String SQL_SELECT_TASK = "select \"GemsCount\" from \"Player\" where \"PlayerID\" = '"
                    + getCurrentChatId() + "';";
            ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
            if (result.next()) {
                int count = result.getInt("GemsCount");
                connection.close();
                return count;
            } else {
                connection.close();
                return -1;
            }
        }
    }

}

//🏁 -2 -4 -3 💎 🌳 -3 1,3, -1 🌳 1 🌳 🗿 -2 🌳 2 🌳 🌳, 2 💎 -1 -2 3 🐸
// {"💎", -2, -4, -3, "💎"},
//                {"🌳", -3, 1, "🪓", "🐸"},
//                {"🌳", 1, 1, "🗿", -2},
//                {"🌳", 2, "🌳", "🌳", 2},
//                {"🏁", -1, -2, -1, 3}};