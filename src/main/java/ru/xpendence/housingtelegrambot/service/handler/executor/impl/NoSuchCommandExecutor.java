package ru.xpendence.housingtelegrambot.service.handler.executor.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 22.02.2021
 */
@Component
public class NoSuchCommandExecutor implements CommandExecutor {

    @Override
    public SendMessage execute(Query query) {
        return MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                "Я тебя не понял. Похоже, была опечатка или была введена неверная команда \uD83D\uDE31 Давай начнём сначала. Отправь мне команду /start",
                true
        );
    }
}
