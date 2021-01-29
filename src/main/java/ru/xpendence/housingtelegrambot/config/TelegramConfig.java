package ru.xpendence.housingtelegrambot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.xpendence.housingtelegrambot.controller.HousingBotApi;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@Configuration
@RequiredArgsConstructor
public class TelegramConfig {

    private final HousingBotApi housingBotApi;

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(housingBotApi);
        return api;
    }
}
