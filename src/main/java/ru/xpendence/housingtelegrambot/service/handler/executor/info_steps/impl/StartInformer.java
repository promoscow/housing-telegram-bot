package ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.model.cache.UserChooseCache;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.service.handler.executor.builders.AbstractButtonsBuilder;
import ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.Informer;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.stream.Collectors;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
@Component("INFO_START")
@RequiredArgsConstructor
public class StartInformer extends AbstractButtonsBuilder implements Informer {

    private final FlatService flatService;
    private final ChatUserService chatUserService;
    private final CacheManager cacheManager;

    private static final String CHOOSE_HOUSING_TEXT = "Окей! Укажи корпус, который тебя интересует.";

    @Override
    public SendMessage inform(Query query) {
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(InteractionStep.INFO_HOUSING);
        chatUserService.update(chatUser);
        cacheManager.saveOrUpdate(new UserChooseCache(chatUser.getId()));

        return prepareSelectHousingButtons(chatUser);
    }

    protected SendMessage prepareSelectHousingButtons(ChatUser chatUser) {
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
