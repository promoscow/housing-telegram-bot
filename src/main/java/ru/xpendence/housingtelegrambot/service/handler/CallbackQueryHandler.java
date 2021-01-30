package ru.xpendence.housingtelegrambot.service.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.Query;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component
public class CallbackQueryHandler implements Handler {

    @Override
    public SendMessage handle(Query query) {
        return null;
    }
}
