package ru.xpendence.housingtelegrambot.service.handler.executor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;

import java.util.Map;
import java.util.Objects;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("/update")
@RequiredArgsConstructor
public class UpdateCommandExecutor implements CommandExecutor {

    private final Map<String, Updater> updaters;

    @Override
    public SendMessage execute(Query query) {
        return updaters.get(defineUpdateStep(query).name()).update(query);
    }

    private InteractionStep defineUpdateStep(Query query) {
        return Objects.isNull(query.getChatUser().getInteractionStep())
                ? InteractionStep.UPDATE_START
                : query.getChatUser().getInteractionStep();
    }
}
