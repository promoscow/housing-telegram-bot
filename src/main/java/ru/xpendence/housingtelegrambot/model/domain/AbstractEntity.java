package ru.xpendence.housingtelegrambot.model.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.xpendence.housingtelegrambot.model.domain.enums.ActiveType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private ActiveType active;

    @PrePersist
    void onCreate() {
        if (Objects.isNull(this.getCreated())) {
            this.created = LocalDateTime.now();
        }
        this.active = ActiveType.ENABLED;
    }

    @PreUpdate
    void onUpdate() {
        this.setUpdated(LocalDateTime.now());
    }
}
