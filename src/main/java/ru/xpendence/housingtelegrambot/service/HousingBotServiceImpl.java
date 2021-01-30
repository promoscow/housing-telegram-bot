package ru.xpendence.housingtelegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.mapper.ChatUserMapper;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
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
    private final ChatUserMapper chatUserMapper;
    private final ChatUserService chatUserService;

    @Override
    public SendMessage onUpdateReceived(Update update) {
        if (isCommand(update)) {
            var chatUser = chatUserService.getOrSave(chatUserMapper.map(update.getMessage().getFrom()));
            return commandHandler.handle(Query.ofCommand(update, chatUser));
        }
        if (isCallbackQuery(update)) {
            var chatUser = chatUserService.getOrSave(chatUserMapper.map(update.getCallbackQuery().getFrom()));
            return commandHandler.handle(Query.ofCallbackQuery(update, chatUser));
        }
        return null;
    }

    private boolean isCommand(Update update) {
        return Objects.nonNull(update.getMessage()) && update.getMessage().isCommand();
    }

    private boolean isCallbackQuery(Update update) {
        return update.hasCallbackQuery();
    }
}
