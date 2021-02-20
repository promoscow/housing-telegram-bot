package ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
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
@Component("INFO_FLOOR")
@RequiredArgsConstructor
public class FloorInformer extends AbstractButtonsBuilder implements Informer {

    private final FlatService flatService;
    private final ChatUserService chatUserService;
    private final CacheManager cacheManager;

    @Override
    public SendMessage inform(Query query) {
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(InteractionStep.INFO_FLAT);
        chatUserService.update(chatUser);

        var cache = cacheManager.get(chatUser.getId());

        var floor = Short.parseShort(query.getText());
        cache.setFloor(floor);
        cacheManager.saveOrUpdate(cache);


        var message = MessageBuilder.build(
                chatUser.getTelegramId().toString(),
                "Выбери квартиру.",
                false
        );
        var buttonValues = flatService.getAvailableFlats(cache.getHousing(), cache.getSection(), floor)
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.toList());
        message.setReplyMarkup(composeButtons(buttonValues));
        return message;
    }
}
