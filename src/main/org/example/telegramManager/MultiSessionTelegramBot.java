package main.org.example.telegramManager;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiSessionTelegramBot extends TelegramLongPollingBot {
    private String name;
    private String token;

    private ThreadLocal<Update> updateEvent = new ThreadLocal<>();

    List<Message> sendMessages = new ArrayList<>();

    public MultiSessionTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public final void onUpdateReceived(Update updateEvent) {
        this.updateEvent.set(updateEvent);
        try {
            onUpdateEventReceived(this.updateEvent.get());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void onUpdateEventReceived(Update updateEvent) throws FileNotFoundException {
    }

    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public int getSendMessageID(SendMessage sendMessage) {
        for (Message message : sendMessages) {
            if (message.getText().equals(sendMessage.getText()))
                return message.getMessageId();
        }
        return -1;
    }

    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    public void sendTextMessageAsync(String text) {
        try {
            SendMessage message = createMessage(text);
            var task = sendApiMethodAsync(message);
            this.sendMessages.add(task.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTextMessageAsync(String text, Map<String, String> buttons) {
        try {
            SendMessage message = createMessage(text, buttons);
            var task = sendApiMethodAsync(message);
            this.sendMessages.add(task.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPhotoMessageAsync(String photoKey) {
        SendPhoto photo = createPhotoMessage(photoKey);
        executeAsync(photo);
    }

    public void sendImageMessageAsync(String imagePath) {
        SendPhoto photo = createPhotoMessage(Path.of(imagePath));
        executeAsync(photo);
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("HTML");
        message.enableHtml(true);
        Long chatId = getCurrentChatId();
        message.setChatId(chatId);
        return message;
    }

    public SendMessage createMessage(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text);
        if (buttons != null && !buttons.isEmpty())
            attachButtons(message, buttons);
        return message;
    }

    private void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    private void attachButtons(EditMessageText message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public SendPhoto createPhotoMessage(String name) {
        try {
            var is = ClassLoader.getSystemResourceAsStream("images/" + name + ".jpg");
            return createPhotoMessage(is);
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    public SendPhoto createPhotoMessage(Path path) {
        try {
            var is = Files.newInputStream(path);
            return createPhotoMessage(is);
        } catch (IOException e) {
            throw new RuntimeException("Can't create image message!");
        }
    }

    public Message getLastSentMessage() {
        if (this.sendMessages.isEmpty()) return null;
        return this.sendMessages.get(this.sendMessages.size() - 1);
    }

    public List<Message> getAllSentMessages() {
        return this.sendMessages;
    }

    public void editTextMessageAsync(Integer messageId, String text) {
        try {
            EditMessageText command = new EditMessageText();
            command.setChatId(getCurrentChatId());
            command.setMessageId(messageId);
            command.setText(text);
            executeAsync(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editTextMessageAsync(Integer messageId, String text, Map<String, String> buttons) {
        try {
            EditMessageText command = new EditMessageText();
            command.setChatId(getCurrentChatId());
            command.setMessageId(messageId);
            command.setText(text);
            command.setParseMode("HTML");
            command.enableHtml(true);
            executeAsync(command);
            attachButtons(command, buttons);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SendPhoto createPhotoMessage(InputStream inputStream) {
        try {
            InputFile inputFile = new InputFile();
            inputFile.setMedia(inputStream, name);

            SendPhoto photo = new SendPhoto();
            photo.setPhoto(inputFile);
            Long chatId = getCurrentChatId();
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }
}
