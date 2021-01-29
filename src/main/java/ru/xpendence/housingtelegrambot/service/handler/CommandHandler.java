package ru.xpendence.housingtelegrambot.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;

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

    @Override
    public SendMessage handle(Update update) {
        return executors.get(update.getMessage().getText()).execute(update);
    }
}
