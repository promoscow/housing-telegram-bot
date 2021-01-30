package ru.xpendence.housingtelegrambot.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SQLDelete(sql = "update flats set active = 'DISABLED' where id = ?")
@Entity
@Table(name = "flats")
@Where(clause = "active = 'ENABLED'")
public class Flat extends AbstractEntity {

    @Column(name = "housing")
    private Short housing;

    @Column(name = "section")
    private Short section;

    @Column(name = "floor")
    private Short floor;

    @Column(name = "flat")
    private Short flat;

    @ManyToMany(mappedBy = "flats")
    private List<ChatUser> chatUsers;
}
