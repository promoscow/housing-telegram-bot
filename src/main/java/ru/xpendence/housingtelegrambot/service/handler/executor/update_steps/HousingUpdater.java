package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("HOUSING")
@RequiredArgsConstructor
public class HousingUpdater implements Updater {

    private final FlatService flatService;

    @Override
    public SendMessage update(Query query) {
        var flat = flatService.getOrSave(query.getChatUser());
        flat.setHousing(query.getHousing());
        flatService.update(flat);

        return MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(), "Введите секцию", false
        );
    }
}
