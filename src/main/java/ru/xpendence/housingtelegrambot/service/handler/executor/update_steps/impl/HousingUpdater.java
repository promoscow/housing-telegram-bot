package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
 * @since 30.01.2021
 */
@Component("UPDATE_HOUSING")
@RequiredArgsConstructor
public class HousingUpdater implements Updater {

    private final FlatService flatService;
    private final ChatUserService chatUserService;

    @Override
    public SendMessage update(Query query) {
        var flat = flatService.getOrSave(query.getChatUser());
        flat.setHousing(Short.parseShort(query.getText()));
        flatService.update(flat);
        query.getChatUser().setInteractionStep(InteractionStep.UPDATE_SECTION);
        chatUserService.update(query.getChatUser());

        return MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                "Отлично, корпус записан! Теперь давай введём секцию. Просто напиши мне сообщение с номером.",
                false
        );
    }
}
