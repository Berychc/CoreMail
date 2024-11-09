package com.example.mail.service;

import com.example.mail.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Сервис для отправки электронных писем.
 * Использует RabbitMQ для получения сообщений о событиях и отправки соответствующих писем.
 */
@Component
@EnableRabbit
public class MailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Обработчик сообщений из очереди "mail".
     * Получает сообщение в виде массива байтов, преобразует его в объект Event
     * и отправляет электронное письмо с информацией, содержащейся в этом объекте.
     *
     * @param message Массив байтов, представляющий сообщение из очереди.
     * @throws RuntimeException Если возникает ошибка при обработке сообщения или отправке письма.
     */
    @RabbitListener(queues = "mail")
    public void sendMail(byte[] message) {
        try {
            Event event = objectMapper.readValue(message, Event.class);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(event.getEmail());
            mailMessage.setSubject("Уведомление");
            mailMessage.setText(event.getDescription());
            sender.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Сообщение об ошибке обработки: " + e.getMessage(), e);
        }
    }
}
