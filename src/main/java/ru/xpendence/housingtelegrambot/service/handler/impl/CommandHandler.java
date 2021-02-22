package ru.xpendence.housingtelegrambot.service.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.Handler;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.service.handler.executor.impl.NoSuchCommandExecutor;

import java.util.Map;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component
@RequiredArgsConstructor
public class CommandHandler implements Handler {

    private final Map<String, CommandExecutor> executors;
    private final NoSuchCommandExecutor noSuchCommandExecutor;

    @Override
    public SendMessage handle(Query query) {
        try {
            return executors.get(query.getCommand()).execute(query);
        } catch (NullPointerException e) {
            return noSuchCommandExecutor.execute(query);
        }
    }
}
