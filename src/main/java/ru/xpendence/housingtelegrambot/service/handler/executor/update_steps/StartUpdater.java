package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component("START")
@RequiredArgsConstructor
public class StartUpdater implements Updater {

    private final FlatService flatService;

    private static final String CHOOSE_HOUSING_TEXT = "Выберите корпус";

    @Override
    public SendMessage update(Query query) {
        flatService.getOrSave(query.getChatUser());
        var message = MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(), CHOOSE_HOUSING_TEXT, true
        );
        message.setReplyMarkup(composeButtons());
        return message;
    }

    private InlineKeyboardMarkup composeButtons() {
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
        button.setCallbackData("/update HOUSING " + housing);
        return button;
    }
}
