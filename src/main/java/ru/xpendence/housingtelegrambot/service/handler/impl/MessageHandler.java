package ru.xpendence.housingtelegrambot.service.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.Handler;
import ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.Informer;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;

import java.util.Map;
import java.util.Objects;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@Component
@RequiredArgsConstructor
public class MessageHandler implements Handler {

    private final Map<String, Updater> updaters;
    private final Map<String, Informer> informers;

    @Override
    public SendMessage handle(Query query) {
        var step = query.getChatUser().getInteractionStep();
        if (Objects.nonNull(step)) {
            if (step.name().matches("^(UPDATE_)\\D*$")) {
                return updaters.get(step.name()).update(query);
            }
            if (step.name().matches("^(INFO_)\\D*$")) {
                return informers.get(step.name()).inform(query);
            }
        }
        return null;
    }
}
