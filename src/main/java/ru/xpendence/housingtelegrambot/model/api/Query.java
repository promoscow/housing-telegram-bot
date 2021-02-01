package ru.xpendence.housingtelegrambot.model.api;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.model.api.enums.InteractionStep;
import ru.xpendence.housingtelegrambot.model.domain.ChatUser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 30.01.2021
 */
@Data
public class Query {

    private Long chatId;
    private ChatUser chatUser;
    private String text;
    private boolean privateChatWithQueryAuthor;
    private InteractionStep interactionStep;

    public static Query ofText(Update update, ChatUser chatUser) {
        var query = new Query();
        var message = update.getMessage();
        query.chatId = message.getChatId();
        query.chatUser = chatUser;
        query.text = message.getText();
        query.privateChatWithQueryAuthor = query.isPrivateChatWithQueryAuthor(update.getMessage());
        return query;
    }

    public static Query ofCallbackQuery(Update update, ChatUser chatUser) {
        var query = new Query();
        var message = update.getCallbackQuery().getMessage();
        query.chatId = message.getChatId();
        query.chatUser = chatUser;
        query.text = update.getCallbackQuery().getData();
        return query;
    }

    public String getCommand() {
        return Arrays.stream(this.text.split(" "))
                .filter(e -> e.matches("^/\\D+$"))
                .collect(Collectors.joining());
    }

    private boolean isPrivateChatWithQueryAuthor(Message message) {
        return message.getChat().isUserChat() && message.getChat().getUserName().equals(message.getFrom().getUserName());
    }
}
