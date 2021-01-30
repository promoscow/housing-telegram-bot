package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("HOUSING")
public class HousingUpdater implements Updater {

    @Override
    public SendMessage update(Query query) {
        return null;
    }
}
