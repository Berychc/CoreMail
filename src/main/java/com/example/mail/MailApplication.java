package com.example.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Главный класс приложения для отправки электронной почты.
 * Это точка входа в приложение, которое настраивает Spring Boot.
 */
@SpringBootApplication
@EnableAsync
public class MailApplication {

    /**
     * Метод main, который запускает приложение.
     *
     * @param args Массив строк с аргументами командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

}
