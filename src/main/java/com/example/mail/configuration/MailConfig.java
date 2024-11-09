package com.example.mail.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Конфигурация для отправки электронных писем с использованием JavaMailSender.
 * Эта конфигурация использует SMTP-сервер Gmail для отправки писем.
 */
@Configuration
public class MailConfig {

    /**
     * Создает и настраивает bean JavaMailSender.
     *
     * @return настроенный объект JavaMailSender для отправки писем.
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("treewincsgo@gmail.com");
        mailSender.setPassword("clbc tvga lkmo nayq");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
