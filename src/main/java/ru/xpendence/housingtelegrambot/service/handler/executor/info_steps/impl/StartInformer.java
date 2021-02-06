package ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.executor.builders.AbstractButtonsBuilder;
import ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.Informer;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
@Component("INFO_START")
@RequiredArgsConstructor
public class StartInformer extends AbstractButtonsBuilder implements Informer {

    @Override
    public SendMessage inform(Query query) {
        var message = MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                "Выберите корпус",
                true
                );
//        message.setReplyMarkup(composeButtons());
        return message;
    }
}
