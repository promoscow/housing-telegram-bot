package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.UpdateStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 31.01.2021
 */
@Component("SECTION")
@RequiredArgsConstructor
public class SectionUpdater implements Updater {

    private final FlatService flatService;
    private final ChatUserService chatUserService;

    @Override
    public SendMessage update(Query query) {
        var flat = flatService.getOrSave(query.getChatUser());
        flat.setSection(Short.parseShort(query.getText()));
        flatService.update(flat);
        query.getChatUser().setUpdateStep(UpdateStep.FLOOR);
        chatUserService.update(query.getChatUser());

        return MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                "Мы записали секцию, поздравляю! Идём дальше: введи этаж.",
                false
        );
    }
}
