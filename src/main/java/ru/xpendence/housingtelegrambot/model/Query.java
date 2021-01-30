package ru.xpendence.housingtelegrambot.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Data
public class Query {

    private Long chatId;
    private User user;
    private String text;
    private boolean privateChatWithQueryAuthor;

    public static Query ofCommand(Update update) {
        var query = new Query();
        var message = update.getMessage();
        query.chatId = message.getChatId();
        query.user = message.getFrom();
        query.text = message.getText();
        query.privateChatWithQueryAuthor = query.isPrivateChatWithQueryAuthor(update.getMessage());
        return query;
    }

    public static Query ofCallbackQuery(Update update) {
        var query = new Query();
        var message = update.getCallbackQuery().getMessage();
        query.chatId = message.getChatId();
        query.user = message.getFrom();
        query.text = update.getCallbackQuery().getData();
        return query;
    }

    private boolean isPrivateChatWithQueryAuthor(Message message) {
        return message.getChat().isUserChat() && message.getChat().getUserName().equals(message.getFrom().getUserName());
    }
}
