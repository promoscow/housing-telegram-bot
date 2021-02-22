package ru.xpendence.housingtelegrambot.service.handler.executor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.service.handler.executor.auto_adding_steps.CarUpdater;

import java.util.Map;
import java.util.Objects;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 22.02.2021
 */
@Component("/auto")
@RequiredArgsConstructor
public class CarCommandExecutor implements CommandExecutor {

    private final Map<String, CarUpdater> carUpdaters;

    @Override
    public SendMessage execute(Query query) {
        return carUpdaters.get(defineCarUpdateStep(query).name()).update(query);
    }

    private InteractionStep defineCarUpdateStep(Query query) {
        return Objects.isNull(query.getChatUser().getInteractionStep())
                ? InteractionStep.CAR_START
                : query.getChatUser().getInteractionStep();
    }
}
