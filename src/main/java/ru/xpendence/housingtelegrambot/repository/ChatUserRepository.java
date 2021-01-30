package ru.xpendence.housingtelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;

import java.util.Optional;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
public interface ChatUserRepository extends JpaRepository<ChatUser, String> {

    Optional<ChatUser> getByTelegramId(Long telegramId);
}
