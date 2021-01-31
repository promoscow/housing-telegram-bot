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
    private final ChatUserService chatUserService;

    public Flat save(Flat flat, ChatUser chatUser) {
        var saved = flatRepository.save(flat);
        chatUser.setFlat(saved);
        chatUserService.save(chatUser);
        return get(saved.getId());
    }

    public Flat update(Flat flat) {
        return flatRepository.save(flat);
    }

    // TODO: 30.01.2021 caching
    public Optional<Flat> getForUser(String chatUserId) {
        return flatRepository.getForUser(chatUserId);
    }

    public Flat get(String id) {
        return flatRepository.getOne(id);
    }

    public Flat getOrSave(ChatUser chatUser) {
        return getForUser(chatUser.getId()).orElseGet(() -> save(new Flat(), chatUser));
    }
}
