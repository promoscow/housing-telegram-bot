package ru.xpendence.housingtelegrambot.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;
import ru.xpendence.housingtelegrambot.model.domain.Flat;
import ru.xpendence.housingtelegrambot.repository.FlatRepository;

import java.util.Optional;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Service
@RequiredArgsConstructor
public class FlatService {

    private final FlatRepository flatRepository;

    public Flat save(Flat flat) {
        return flatRepository.save(flat);
    }

    // TODO: 30.01.2021 caching
    public Optional<Flat> getForUser(String chatUserId) {
        return flatRepository.getByChatUserId(chatUserId);
    }

    public Flat getOrSave(ChatUser chatUser) {
        return getForUser(chatUser.getId()).orElseGet(() -> save(Flat.newOf(chatUser)));
    }
}
