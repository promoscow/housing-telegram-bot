package ru.xpendence.housingtelegrambot.cache;

import org.springframework.stereotype.Component;
import ru.xpendence.housingtelegrambot.model.cache.UpdateCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 06.02.2021
 */
@Component
public class CacheManager {

    private Map<String, UpdateCache> cacheMap = new HashMap<>();

    public void saveOrUpdate(UpdateCache cache) {
        cacheMap.put(cache.getChatUserId(), cache);
    }

    public UpdateCache get(String chatUserId) {
        return cacheMap.get(chatUserId);
    }
}
