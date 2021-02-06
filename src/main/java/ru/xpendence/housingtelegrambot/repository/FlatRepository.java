package ru.xpendence.housingtelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.xpendence.housingtelegrambot.model.domain.Flat;

import java.util.List;
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


    @Query("select distinct f.housing from Flat f")
    List<Short> getDistinctByHousing();

    @Query("select distinct f.section from Flat f where f.housing = :housing")
    List<Short> getDistinctBySection(@Param("housing") Short housing);

    @Query("select distinct f.floor from Flat f where f.housing = :housing and f.section = :section")
    List<Short> getDistinctByFloor(@Param("housing") Short housing, @Param("section") Short section);

    @Query("select distinct f.flat from Flat f where f.housing = :housing and f.section = :section and f.floor = :floor")
    List<Short> getDistinctByFlat(@Param("housing") Short housing, @Param("section") Short section, @Param("floor") Short floor);

    @Query("select f from Flat f where f.housing = :housing and f.flat = :flat")
    Flat getByFlat(@Param("housing") Short housing, @Param("flat") Short flat);
}
