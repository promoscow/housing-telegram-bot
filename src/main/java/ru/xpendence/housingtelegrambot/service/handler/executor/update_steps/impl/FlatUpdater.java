package ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.handler.executor.update_steps.Updater;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 31.01.2021
 */
@Component("UPDATE_FLAT")
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

    private final CacheManager cacheManager;
    private final ChatUserService chatUserService;

    @Override
    public SendMessage update(Query query) {
        var chatUser = query.getChatUser();

        var cache = cacheManager.get(chatUser.getId());
        var flat = Short.parseShort(query.getText());
        cache.setFlat(flat);
        cacheManager.saveOrUpdate(cache);

        chatUser.setInteractionStep(InteractionStep.UPDATE_CONFIRMATION);
        chatUserService.update(chatUser);

        var message = MessageBuilder.build(
                query.getChatUser().getTelegramId().toString(),
                String.format(CONGRATS_TEXT, cache.getHousing(), cache.getSection(), cache.getFloor(), flat),
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
