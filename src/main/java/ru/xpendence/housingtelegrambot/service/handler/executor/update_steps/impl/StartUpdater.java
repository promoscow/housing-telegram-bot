package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.model.cache.UpdateCache;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.service.handler.executor.builders.AbstractButtonsBuilder;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.stream.Collectors;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("UPDATE_START")
@RequiredArgsConstructor
public class StartUpdater extends AbstractButtonsBuilder implements Updater {

    private final FlatService flatService;
    private final ChatUserService chatUserService;
    private final CacheManager cacheManager;

    private static final String CHOOSE_HOUSING_TEXT = "Окей! Укажи корпус, в котором ты живёшь. Просто нажми нужную кнопку ниже.";

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();
        cacheManager.saveOrUpdate(new UpdateCache(chatUser.getId()));
        chatUser.setInteractionStep(InteractionStep.UPDATE_HOUSING);
        chatUserService.update(chatUser);

        var message = MessageBuilder.build(
                chatUser.getTelegramId().toString(), CHOOSE_HOUSING_TEXT, true
        );
        var buttonValues = flatService.getAvailableHousings()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        message.setReplyMarkup(composeButtons(buttonValues));
        return message;
    }
}
