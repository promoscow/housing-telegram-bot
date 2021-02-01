package ru.xpendence.housingtelegrambot.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SQLDelete(sql = "update flats set active = 'DISABLED' where id = ?")
@Entity
@Table(name = "flats")
@Where(clause = "active = 'ENABLED'")
// TODO: 01.02.2021 серьёзная проблема, если в квартире уже кто-то живёт
// TODO: 01.02.2021 обновлять квартиру и дописывать туда жильца
// TODO: 01.02.2021 или лучше при старте создавать в базе квартиры и "подселять" туда жильцов
public class Flat extends AbstractEntity {

    @Column(name = "housing")
    private Short housing;

    @Column(name = "section")
    private Short section;

    @Column(name = "floor")
    private Short floor;

    @Column(name = "flat")
    private Short flat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flat")
    private List<ChatUser> chatUsers;
}
