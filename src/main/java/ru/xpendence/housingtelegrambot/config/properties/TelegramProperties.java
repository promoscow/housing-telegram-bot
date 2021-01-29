package ru.xpendence.housingtelegrambot.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@ConfigurationProperties("telegram")
@Getter
@Setter
public class TelegramProperties {

    private String bot;
    private String token;
}
