package ru.xpendence.housingtelegrambot.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SQLDelete(sql = "update chat_users set active = 'DISABLED' where id = ?")
@Entity
@Table(
        name = "chat_users",
        indexes = {
                @Index(columnList = "telegram_id", name = "telegram_id_idx")
        })
@Where(clause = "active = 'ENABLED'")
public class ChatUser extends AbstractEntity {

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private Flat flat;
}
