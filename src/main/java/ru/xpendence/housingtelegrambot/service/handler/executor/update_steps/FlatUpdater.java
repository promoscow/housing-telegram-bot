package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.UpdateStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 31.01.2021
 */
@Component("FLAT")
@RequiredArgsConstructor
public class FlatUpdater implements Updater {

    private final static String CONGRATS_TEXT = """
            Поздравляю! Давай проверим информацию. Я записал следующие:
            
            Корпус: %d
            Секция: %d
            Этаж: %d
            Квартира: %d.
            
            Всё верно?
            """;

    private final FlatService flatService;
    private final ChatUserService chatUserService;

    @Override
    public SendMessage update(Query query) {
        var flat = flatService.getOrSave(query.getChatUser());
        flat.setFlat(Short.parseShort(query.getText()));
        flat = flatService.update(flat);
        query.getChatUser().setUpdateStep(UpdateStep.CONFIRMATION);
        chatUserService.update(query.getChatUser());

        var message = MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                String.format(CONGRATS_TEXT, flat.getHousing(), flat.getSection(), flat.getFloor(), flat.getFlat()),
                true
        );

        message.setReplyMarkup(composeButtons());
        return message;
    }

    private InlineKeyboardMarkup composeButtons() {
        var markup = new InlineKeyboardMarkup();
        var yesButton = createYesButton();
        var noButton = createNoButton();

        var buttons = Lists.newArrayList(yesButton, noButton);
        var keyboard = new ArrayList<List<InlineKeyboardButton>>();
        keyboard.add(buttons);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardButton createYesButton() {
        var button = new InlineKeyboardButton();
        button.setText("Да, всё верно");
        button.setCallbackData("yes");
        return button;
    }

    private InlineKeyboardButton createNoButton() {
        var button = new InlineKeyboardButton();
        button.setText("Нет, я ошибся");
        button.setCallbackData("no");
        return button;
    }
}
