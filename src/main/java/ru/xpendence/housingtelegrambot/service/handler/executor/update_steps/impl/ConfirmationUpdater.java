package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
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

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();
        if ("yes".equals(query.getText())) {
            chatUser.setInteractionStep(null);
            chatUser.setRegistered(true);
            chatUserService.update(chatUser);
            return MessageBuilder.build(
                    chatUser.getTelegramId().toString(),
                    "Отлично, я всё сохранил. Если захочешь воспользоваться моими услугами, напиши мне /start.",
                    false
            );
        } else {
            chatUser.setInteractionStep(InteractionStep.UPDATE_START);
            chatUserService.update(chatUser);
            return startUpdater.update(query);
        }
    }
}
