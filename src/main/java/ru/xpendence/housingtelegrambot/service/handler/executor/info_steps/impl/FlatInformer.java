package ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.xpendence.housingtelegrambot.cache.CacheManager;
import ru.xpendence.housingtelegrambot.model.api.Query;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;
import ru.xpendence.housingtelegrambot.model.domain.Flat;
import ru.xpendence.housingtelegrambot.service.domain.ChatUserService;
import ru.xpendence.housingtelegrambot.service.domain.FlatService;
import ru.xpendence.housingtelegrambot.service.handler.executor.info_steps.Informer;
import ru.xpendence.housingtelegrambot.util.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 01.02.2021
 */
@Component("INFO_FLAT")
@RequiredArgsConstructor
public class FlatInformer implements Informer {

    private static final String INFO_MESSAGE = """
            Корпус: %d
            Секция: %d
            Этаж: %d
            Квартира: %d
                
            %s
            
            🏢🏫🏠🏪🏬
                        
            Продолжим? Напиши /start
            """;

    private final CacheManager cacheManager;
    private final ChatUserService chatUserService;
    private final FlatService flatService;

    @Override
    public SendMessage inform(Query query) {
        var chatUser = query.getChatUser();
        var cache = cacheManager.get(chatUser.getId());
        var flat = flatService.getByFlat(cache.getHousing(), Short.parseShort(query.getText()));
        chatUser.setInteractionStep(null);
        chatUser.setRegistered(true);
        chatUserService.update(chatUser);

        var users = chatUserService.getAllByFlat(flat);

        return MessageBuilder.build(
                chatUser.getTelegramId().toString(),
                composeInfoMessage(flat, users),
                false
        );
    }

    private String composeInfoMessage(Flat flat, List<ChatUser> users) {
        var usersAsString = users
                .stream()
                .map(u -> String.format("%s %s\n@%s\n\n", u.getFirstName(), u.getLastName(), u.getUserName()))
                .collect(Collectors.joining())
                .trim();
        return String.format(
                INFO_MESSAGE,
                flat.getHousing(),
                flat.getSection(),
                flat.getFloor(),
                flat.getFlat(),
                usersAsString.isBlank() ? "В этой квартире никто не живёт \uD83D\uDE10" : "Эта квартира принадлежит следующим собственникам:\n\n" + usersAsString
        );
    }
}
