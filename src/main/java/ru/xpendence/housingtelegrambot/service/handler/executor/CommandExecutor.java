package ru.xpendence.housingtelegrambot.service.handler.executor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.Query;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
public interface CommandExecutor {

    SendMessage execute(Query query);
}
