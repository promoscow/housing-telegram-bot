package ru.xpendence.housingtelegrambot.service.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@Component
public class MessageHandler implements Handler {

    @Override
    public SendMessage handle(Update update) {
        return null;
    }
}
