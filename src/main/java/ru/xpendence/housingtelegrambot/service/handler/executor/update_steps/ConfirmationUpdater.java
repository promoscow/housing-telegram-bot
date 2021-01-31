package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.UpdateStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 31.01.2021
 */
@Component("CONFIRMATION")
@RequiredArgsConstructor
public class ConfirmationUpdater implements Updater {

    private final ChatUserService chatUserService;
    private final StartUpdater startUpdater;

    @Override
    public SendMessage update(Query query) {
        if ("yes".equals(query.getText())) {
            query.getChatUser().setUpdateStep(null);
            chatUserService.update(query.getChatUser());
            return MessageBuilder.build(
                    query.getChatUser().getTelegramId().toString(),
                    "Отлично, я всё сохранил. Если захочешь воспользоваться моими услугами, напиши мне /start.",
                    false
            );
        } else {
            query.getChatUser().setUpdateStep(UpdateStep.START);
            chatUserService.update(query.getChatUser());
            return startUpdater.update(query);
        }
    }
}
