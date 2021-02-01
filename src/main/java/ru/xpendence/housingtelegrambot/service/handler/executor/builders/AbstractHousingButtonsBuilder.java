package ru.xpendence.housingtelegrambot.service.handler.executor.builders;

import com.google.common.collect.Lists;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
public abstract class AbstractHousingButtonsBuilder {

    protected InlineKeyboardMarkup composeButtons() {
        var markup = new InlineKeyboardMarkup();
        var housingOneButton = createHousingButton("1");
        var housingTwoButton = createHousingButton("2");
        var housingTreeButton = createHousingButton("3");
        var housingFourButton = createHousingButton("4");

        var buttons = Lists.newArrayList(
                housingOneButton, housingTwoButton, housingTreeButton, housingFourButton
        );
        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        keyboard.add(buttons);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardButton createHousingButton(String housing) {
        var button = new InlineKeyboardButton();
        button.setText(housing);
        button.setCallbackData(housing);
        return button;
    }
}
