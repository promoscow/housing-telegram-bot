package ru.xpendence.housingtelegrambot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.xpendence.housingtelegrambot.config.properties.TelegramProperties;
import ru.xpendence.housingtelegrambot.service.HousingBotService;

/**
 * Контроллер для получения обновлений для бота.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@Component
@RequiredArgsConstructor
public class HousingBotApi extends TelegramLongPollingBot {

    private final TelegramProperties telegramProperties;
    private final HousingBotService housingBotService;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(housingBotService.onUpdateReceived(update));
        } catch (TelegramApiException e) {
            // TODO: 30.01.2021
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getBot();
    }

    @Override
    public String getBotToken() {
        return telegramProperties.getToken();
    }
}
