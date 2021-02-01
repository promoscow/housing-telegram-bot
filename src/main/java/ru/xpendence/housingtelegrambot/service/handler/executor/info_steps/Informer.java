package ru.xpendence.housingtelegrambot.service.handler.executor.info_steps;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
public interface Informer {

    SendMessage inform(Query query);
}
