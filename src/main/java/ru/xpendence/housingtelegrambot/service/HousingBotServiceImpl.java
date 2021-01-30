package ru.xpendence.housingtelegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.model.Query;
import ru.xpendence.housingtelegrambot.service.handler.CommandHandler;

import java.util.Objects;

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
            return commandHandler.handle(Query.ofCommand(update));
        }
        if (isCallbackQuery(update)) {
            return commandHandler.handle(Query.ofCallbackQuery(update));
        }

        if (fromPrivateChat(update)) {
            return handleMessageFromPrivateChat(update);
        }
        if (fromGroupChat(update)) {
            return handleMessageFromGroupChat(update);
        }
        return null;
    }

    private SendMessage handleMessageFromGroupChat(Update update) {
        return null;
    }

    private boolean fromGroupChat(Update update) {
        return false;
    }

    private SendMessage handleMessageFromPrivateChat(Update update) {
        return null;
    }

    private boolean fromPrivateChat(Update update) {
        return false;
    }

    private boolean isCommand(Update update) {
        return Objects.nonNull(update.getMessage()) && update.getMessage().isCommand();
    }

    private boolean isCallbackQuery(Update update) {
        return update.hasCallbackQuery();
    }
}
