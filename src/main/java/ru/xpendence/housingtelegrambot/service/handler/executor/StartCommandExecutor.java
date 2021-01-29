package ru.xpendence.housingtelegrambot.service.handler.executor;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
public class StartCommandExecutor implements CommandExecutor {

    private final static String START_TEXT_MESSAGE = """
        Привет! Я - СкандиБот. Храню информацию по всем жильцам нашего ЖК.
        
        Для начала, давай добавим информацию о тебе. Как минимум, мне нужно знать корпус, секцию и этаж.
        После добавления этой информации ты сможешь смотреть информацию по другим жильцам.
        """;

    @Override
    public SendMessage execute(Update update) {
        // TODO: 30.01.2021 два текста - для указавшего информацию и не указавшего. Две кнопки или одна.
        var message = MessageBuilder.build(
                update.getMessage().getChatId().toString(),
                START_TEXT_MESSAGE,
                true
        );
        message.setReplyMarkup(composeButtons());
        return message;
    }

    private InlineKeyboardMarkup composeButtons() {
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
        button.setText("Добавить инфо о себе");
        button.setCallbackData("/update");
        return button;
    }

    private InlineKeyboardButton createInfoButton() {
        var button = new InlineKeyboardButton();
        button.setText("Инфо");
        button.setCallbackData("/info");
        return button;
    }
}
