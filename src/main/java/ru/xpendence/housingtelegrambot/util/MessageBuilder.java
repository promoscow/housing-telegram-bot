package ru.xpendence.housingtelegrambot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
public class MessageBuilder {

    public static SendMessage build(String chatId, String text, boolean enableMarkdown) {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.enableMarkdown(enableMarkdown);
        return message;
    }
}
