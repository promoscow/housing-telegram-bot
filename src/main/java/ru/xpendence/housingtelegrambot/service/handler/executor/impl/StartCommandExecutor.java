package ru.xpendence.housingtelegrambot.service.handler.executor.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.handler.executor.CommandExecutor;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("/start")
@RequiredArgsConstructor
public class StartCommandExecutor implements CommandExecutor {

    private final ChatUserService chatUserService;

    private final static String START_TEXT_MESSAGE = """
        Привет! Я - СкандиБот 🤖 Храню информацию по всем жильцам нашего ЖК.
        
        Для начала, давай добавим информацию о тебе. Как минимум, мне нужно знать корпус, секцию и этаж.
        После добавления этой информации ты сможешь смотреть информацию по другим жильцам.
        
        Продолжая, ты даёшь согласие на обработку персональных данных, конечно. \uD83D\uDCDD✅ Я сохраню имя и фамилию, указанные тобой в Telegram.
        """;

    private final static String REGISTERED_TEXT_MESSAGE = """
            С возвращением 👾
            
            Ты можешь обновить информацию о себе, добавить автомобиль или запросить информацию о других жильцах.
            """;

    @Override
    public SendMessage execute(Query query) {
        var chatUser = query.getChatUser();
        chatUser.setInteractionStep(null);
        chatUserService.update(chatUser);

        SendMessage message;
        if (Boolean.TRUE.equals(chatUser.getRegistered())) {
            message = MessageBuilder.build(
                    chatUser.getTelegramId().toString(),
                    REGISTERED_TEXT_MESSAGE,
                    true
            );
            message.setReplyMarkup(composeRegisteredButtons());
        } else {
            message = MessageBuilder.build(
                    chatUser.getTelegramId().toString(),
                    START_TEXT_MESSAGE,
                    true
            );
            message.setReplyMarkup(composeStartButtons());
        }
        return message;
    }

    private ReplyKeyboard composeRegisteredButtons() {
        var markup = new InlineKeyboardMarkup();
        var refreshButton = createRefreshButton();
        var autoButton = createAutoButton();
        var infoButton = createInfoButton();

        var buttons = Lists.newArrayList(refreshButton, autoButton, infoButton);
        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        keyboard.add(buttons);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup composeStartButtons() {
        var markup = new InlineKeyboardMarkup();
        var addButton = createAddButton();

        var buttons = Lists.newArrayList(addButton);
        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        keyboard.add(buttons);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardButton createAddButton() {
        var button = new InlineKeyboardButton();
        button.setText("\uD83D\uDCDD Добавить инфо о себе");
        button.setCallbackData("/update");
        return button;
    }

    private InlineKeyboardButton createRefreshButton() {
        var button = new InlineKeyboardButton();
        button.setText("\uD83D\uDCDD Обновить");
        button.setCallbackData("/update");
        return button;
    }

    private InlineKeyboardButton createAutoButton() {
        var button = new InlineKeyboardButton();
        button.setText("\uD83D\uDE97 Добавить авто");
        button.setCallbackData("/auto");
        return button;
    }

    private InlineKeyboardButton createInfoButton() {
        var button = new InlineKeyboardButton();
        button.setText("\uD83C\uDFE0 Инфо");
        button.setCallbackData("/info");
        return button;
    }
}
