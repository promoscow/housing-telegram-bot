package ru.xpendence.housingtelegrambot.service.handler.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 21.02.2021
 */
public interface StepProcessor {

    SendMessage process(Query query);
}
