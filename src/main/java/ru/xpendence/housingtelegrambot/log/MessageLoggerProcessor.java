package ru.xpendence.housingtelegrambot.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xpendence.housingtelegrambot.util.CommonUtils;

/**
 * Описание класса: пару слов что это такое и для чего нужен.
 *
 * @author Вячеслав Чернышов
 * @since 11.02.2021
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MessageLoggerProcessor {

    @Before(value = "@annotation(before) && args(param)")
    public void onUpdate(MessageLogger before, Update param) {
        log.info(">>> {}", CommonUtils.writeValueAsString(param));
    }

    @AfterReturning(value = "@annotation(after)", returning = "response")
    public void onReturn(MessageLogger after, SendMessage response) {
        log.info("<<< {}", CommonUtils.writeValueAsString(response));
    }
}
