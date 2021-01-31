package ru.xpendence.housingtelegrambot.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 29.01.2021
 */
@ConfigurationProperties("telegram")
@Getter
@Setter
@Validated
public class TelegramProperties {

    @NotNull
    private String bot;

    @NotNull
    private String token;
}
