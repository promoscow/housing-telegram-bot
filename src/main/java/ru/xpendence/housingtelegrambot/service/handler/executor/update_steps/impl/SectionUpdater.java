package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
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
 * @since 31.01.2021
 */
@Component("UPDATE_SECTION")
@RequiredArgsConstructor
public class SectionUpdater extends AbstractButtonsBuilder implements Updater {

    private final FlatService flatService;
    private final ChatUserService chatUserService;
    private final CacheManager cacheManager;

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(InteractionStep.UPDATE_FLOOR);
        chatUserService.update(chatUser);

        var section = Short.parseShort(query.getText());
        var cache = cacheManager.get(chatUser.getId());
        cache.setSection(section);
        cacheManager.saveOrUpdate(cache);

        var message = MessageBuilder.build(
                chatUser.getTelegramId().toString(),
                "Мы записали секцию, поздравляю! Идём дальше: выбери этаж.",
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
