package ru.xpendence.housingtelegrambot.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.xpendence.housingtelegrambot.model.domain.Flat;
import ru.xpendence.housingtelegrambot.repository.FlatRepository;

import java.util.List;
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

    private final FlatRepository repository;

    // TODO: 30.01.2021 caching
    public Optional<Flat> getForUser(String chatUserId) {
        return repository.getForUser(chatUserId);
    }

    public Flat get(String id) {
        return repository.getOne(id);
    }

    public Flat getByFlat(Short housing, Short flat) {
        return repository.getByFlat(housing, flat);
    }

    public List<Short> getAvailableHousings() {
        return repository.getDistinctByHousing();
    }

    public List<Short> getAvailableSections(Short housing) {
        return repository.getDistinctBySection(housing);
    }

    public List<Short> getAvailableFloors(Short housing, Short section) {
        return repository.getDistinctByFloor(housing, section);
    }

    public List<Short> getAvailableFlats(Short housing, Short section, Short floor) {
        return repository.getDistinctByFlat(housing, section, floor);
    }
}
