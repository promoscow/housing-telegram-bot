package ru.xpendence.housingtelegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.service.handler.CommandHandler;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@Service
@RequiredArgsConstructor
public class HousingBotServiceImpl implements HousingBotService {

    private final CommandHandler commandHandler;

    @Override
    public SendMessage onUpdateReceived(Update update) {
        if (isCommand(update)) {
            return commandHandler.handle(update);
        }
        return null;
    }

    private boolean isCommand(Update update) {
        return update.getMessage().isCommand();
    }
}
