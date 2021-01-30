package ru.xpendence.housingtelegrambot.model;

import lombok.Data;
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

    public static Query ofCommand(Update update) {
        var query = new Query();
        var message = update.getMessage();
        query.chatId = message.getChatId();
        query.user = message.getFrom();
        query.text = message.getText();
        return query;
    }
}
