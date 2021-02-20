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
@Component("INFO_SECTION")
@RequiredArgsConstructor
public class SectionInformer extends AbstractButtonsBuilder implements Informer {

    private final FlatService flatService;
    private final ChatUserService chatUserService;
    private final CacheManager cacheManager;

    @Override
    public SendMessage inform(Query query) {
        // TODO: 21.02.2021 дубликаты!!!
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(InteractionStep.INFO_FLOOR);
        chatUserService.update(chatUser);

        var section = Short.parseShort(query.getText());
        var cache = cacheManager.get(chatUser.getId());
        cache.setSection(section);
        cacheManager.saveOrUpdate(cache);

        var message = MessageBuilder.build(
                chatUser.getTelegramId().toString(),
                "Выбери этаж.",
                false
        );
        var buttonValues = flatService.getAvailableFloors(cache.getHousing(), section)
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.toList());
        message.setReplyMarkup(composeButtons(buttonValues));
        return message;
    }
}
