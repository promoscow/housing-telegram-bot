package ru.xpendence.housingtelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            value = "select * from flats where id = (select flat_id from chat_users where id = :chatUserId)",
            nativeQuery = true
    )
    Optional<Flat> getForUser(@Param("chatUserId") String chatUserId);
}
