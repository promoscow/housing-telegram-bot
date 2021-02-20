package ru.xpendence.housingtelegrambot.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;
import ru.xpendence.housingtelegrambot.model.domain.Flat;
import ru.xpendence.housingtelegrambot.repository.ChatUserRepository;

import java.util.List;
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

    private final ChatUserRepository repository;

    public ChatUser save(ChatUser chatUser) {
        return repository.save(chatUser);
    }

    public ChatUser update(ChatUser chatUser) {
        return repository.save(chatUser);
    }

    // TODO: 30.01.2021 caching
    public Optional<ChatUser> getByTelegramId(Long telegramId) {
        return repository.getByTelegramId(telegramId);
    }

    public ChatUser getOrSave(ChatUser chatUser) {
        return getByTelegramId(chatUser.getTelegramId()).orElseGet(() -> save(chatUser));
    }

    public List<ChatUser> getAllByFlat(Flat flat) {
        return repository.getAllByFlat(flat);
    }
}
