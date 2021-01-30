package ru.xpendence.housingtelegrambot.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;
import ru.xpendence.housingtelegrambot.repository.ChatUserRepository;

import java.util.Optional;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Service
@RequiredArgsConstructor
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public ChatUser save(ChatUser chatUser) {
        return chatUserRepository.save(chatUser);
    }

    // TODO: 30.01.2021 caching
    public Optional<ChatUser> getByTelegramId(Long telegramId) {
        return chatUserRepository.getByTelegramId(telegramId);
    }

    public ChatUser getOrSave(ChatUser chatUser) {
        return getByTelegramId(chatUser.getTelegramId()).orElseGet(() -> save(chatUser));
    }
}
