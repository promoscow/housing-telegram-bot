package ru.xpendence.housingtelegrambot.service.handler.executor.auto_adding_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.handler.executor.auto_adding_steps.CarUpdater;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 22.02.2021
 */
@Component("CAR_START")
@RequiredArgsConstructor
public class StartCarUpdater implements CarUpdater {

    private final ChatUserService chatUserService;

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(InteractionStep.CAR_CONFIRM);
        chatUserService.update(chatUser);

        return MessageBuilder.build(
                chatUser.getTelegramId().toString(),
                "Введи государственный номер автомобиля \uD83D\uDE99 в формате А000АА00, где А - буква, 0 - цифра",
                true
        );
    }
}
