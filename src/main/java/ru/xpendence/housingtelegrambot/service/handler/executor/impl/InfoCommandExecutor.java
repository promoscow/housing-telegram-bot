package ru.xpendence.housingtelegrambot.service.handler.executor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.Informer;

import java.util.Map;
import java.util.Objects;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
@Component("/info")
@RequiredArgsConstructor
public class InfoCommandExecutor implements CommandExecutor {

    private final Map<String, Informer> informers;

    @Override
    public SendMessage execute(Query query) {
        return informers.get(defineInfoStep(query).name()).inform(query);
    }

    private InteractionStep defineInfoStep(Query query) {
        return Objects.isNull(query.getChatUser().getInteractionStep())
                ? InteractionStep.INFO_START
                : query.getChatUser().getInteractionStep();
    }
}
