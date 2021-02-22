package ru.xpendence.housingtelegrambot.service.handler.executor.auto_adding_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.executor.auto_adding_steps.CarUpdater;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 22.02.2021
 */
@Component("CAR_CONFIRM")
@RequiredArgsConstructor
public class ConfirmCarUpdater implements CarUpdater {

    @Override
    public SendMessage update(Query query) {
        return null;
    }
}
