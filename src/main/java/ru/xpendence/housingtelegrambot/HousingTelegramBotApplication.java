package ru.xpendence.housingtelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.xpendence.housingtelegrambot.config.properties.TelegramProperties;

@EnableConfigurationProperties({
        TelegramProperties.class
})
@SpringBootApplication
public class HousingTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HousingTelegramBotApplication.class, args);
    }
}
