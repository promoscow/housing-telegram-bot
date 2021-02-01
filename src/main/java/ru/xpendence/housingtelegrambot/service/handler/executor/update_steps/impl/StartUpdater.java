package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.service.handler.executor.builders.AbstractHousingButtonsBuilder;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("UPDATE_START")
@RequiredArgsConstructor
public class StartUpdater extends AbstractHousingButtonsBuilder implements Updater {

    private final FlatService flatService;
    private final ChatUserService chatUserService;

    private static final String CHOOSE_HOUSING_TEXT = "Окей! Укажи корпус, в котором ты живёшь. Просто нажми нужную кнопку ниже.";

    @Override
    public SendMessage update(Query query) {
        flatService.getOrSave(query.getChatUser());
        query.getChatUser().setInteractionStep(InteractionStep.UPDATE_HOUSING);
        chatUserService.update(query.getChatUser());

        var message = MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(), CHOOSE_HOUSING_TEXT, true
        );
        message.setReplyMarkup(composeButtons());
        return message;
    }
}
