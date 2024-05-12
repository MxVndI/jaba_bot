package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.example.Messages.*;

public class TelegramMain extends MultiSessionTelegramBot {

    RandomizerMap randomizerMap = new RandomizerMap();

    private Jaba jab;
    private Render render;

    public static final String NAME = "JabaGame"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6325207178:AAEs6KmXC6AfGtlXtc86Py5HZaMj7GPuJu4"; //TODO: добавьте токен бота в кавычках

    public TelegramMain() throws FileNotFoundException {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        if (getMessageText().equals("/start")) {
            sendTextMessageAsync(HELLO_TEXT);
        } else {
                if (getMessageText().equals("начать новую игру")) {
                    startGame();
                    sendMapText();
                }
            if (jab.getChecker().isWin() != true && jab.getChecker().isLose() != true) {
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
                } else {
                    editTextMessageAsync(getLastSentMessage().getMessageId(), "Количество ходов: " + jab.getMoveCount() +
                            "\nКоличество кристаллов: " + jab.getCountGems() +
                            "<pre><code>" + jab.getRender().render(jab.getGameMap().getGameMapArray()) + "</code></pre>",
                            Map.of("⬆️", "up",
                                    "⬅️", "left",
                                    "⬇️", "down",
                                    "➡️", "right"));
                    if (jab.getChecker().isWin() == true) {
                        sendTextMessageAsync(WIN_TEXT);
                    }
                    if (jab.getChecker().isLose() == true) {
                        sendTextMessageAsync(LOSE_TEXT);
                    }
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

    private void startGame() {
        jab = new Jaba();
        render = jab.getRender();
    }
}

//🏁 -2 -4 -3 💎 🌳 -3 1,3, -1 🌳 1 🌳 🗿 -2 🌳 2 🌳 🌳, 2 💎 -1 -2 3 🐸
// {"💎", -2, -4, -3, "💎"},
//                {"🌳", -3, 1, "🪓", "🐸"},
//                {"🌳", 1, 1, "🗿", -2},
//                {"🌳", 2, "🌳", "🌳", 2},
//                {"🏁", -1, -2, -1, 3}};