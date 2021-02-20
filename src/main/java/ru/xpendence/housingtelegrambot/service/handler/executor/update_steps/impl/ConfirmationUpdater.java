package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 31.01.2021
 */
@Component("UPDATE_CONFIRMATION")
@RequiredArgsConstructor
public class ConfirmationUpdater implements Updater {

    private final ChatUserService chatUserService;
    private final StartUpdater startUpdater;
    private final FlatService flatService;
    private final CacheManager cacheManager;

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();

        if ("yes".equals(query.getText())) {
            var cache = cacheManager.get(chatUser.getId());
            var flat = flatService.getByFlat(cache.getHousing(), cache.getFlat());
            chatUser.setInteractionStep(null);
            chatUser.setRegistered(true);
            chatUser.setFlat(flat);
            chatUserService.update(chatUser);
            return MessageBuilder.build(
                    chatUser.getTelegramId().toString(),
                    "Отлично, я всё записал \uD83D\uDCDD✅ Если захочешь воспользоваться моими услугами, напиши мне /start.",
                    false
            );
        } else {
            chatUser.setInteractionStep(InteractionStep.UPDATE_START);
            chatUserService.update(chatUser);
            return startUpdater.update(query);
        }
    }
}
