package ru.xpendence.housingtelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.xpendence.housingtelegrambot.model.domain.Flat;

import java.util.Optional;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
public interface FlatRepository extends JpaRepository<Flat, String> {

    @Query(
            value = "select * from flats as f inner join chat_users as c on c.flat_id = f.id where f.id = :chatUserId",
            nativeQuery = true
    )
    Optional<Flat> getByChatUserId(String chatUserId);
}
