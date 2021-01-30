package ru.xpendence.housingtelegrambot.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Component
public class ChatUserMapper {

    public ChatUser map(User user) {
        var chatUser = new ChatUser();
        chatUser.setTelegramId(user.getId().longValue());
        chatUser.setUserName(user.getUserName());
        chatUser.setFirstName(user.getFirstName());
        chatUser.setLastName(user.getLastName());
        return chatUser;
    }
}
