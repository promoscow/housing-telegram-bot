package ru.xpendence.housingtelegrambot.service.handler.executor.builders;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
public abstract class AbstractButtonsBuilder {

    protected InlineKeyboardMarkup composeButtons(List<String> values) {
        var markup = new InlineKeyboardMarkup();

        var buttons = values
                .stream()
                .map(this::createSimpleButton)
                .collect(Collectors.toList());
        var keyboard = fillKeyboard(buttons);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private ArrayList<List<InlineKeyboardButton>> fillKeyboard(List<InlineKeyboardButton> buttons) {
        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        var counter = 0;
        var row = new ArrayList<InlineKeyboardButton>();
        for (InlineKeyboardButton button : buttons) {
            row.add(button);
            if (++counter == 8) {
                keyboard.add(row);
                row = new ArrayList<>();
            }
        }
        keyboard.add(row);
        return keyboard;
    }

    private InlineKeyboardButton createSimpleButton(String value) {
        var button = new InlineKeyboardButton();
        button.setText(value);
        button.setCallbackData(value);
        return button;
    }
}
