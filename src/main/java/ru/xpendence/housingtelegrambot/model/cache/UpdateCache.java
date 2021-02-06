package ru.xpendence.housingtelegrambot.model.cache;

import lombok.Getter;
import lombok.Setter;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 06.02.2021
 */
@Getter
@Setter
public class UpdateCache {

    private String chatUserId;
    private Short housing;
    private Short section;
    private Short floor;
    private Short flat;

    public UpdateCache(String chatUserId) {
        this.chatUserId = chatUserId;
    }
}
