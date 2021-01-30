package ru.xpendence.housingtelegrambot.service.handler.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;

import java.util.Map;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("/update")
@RequiredArgsConstructor
public class UpdateCommandExecutor implements CommandExecutor {

    private final Map<String, Updater> updaters;

    @Override
    public SendMessage execute(Query query) {
        return updaters.get(query.defineUpdateStep().name()).update(query);
    }
}
