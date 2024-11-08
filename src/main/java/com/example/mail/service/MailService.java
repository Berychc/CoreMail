package com.example.mail.service;

import com.example.mail.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class MailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private ObjectMapper objectMapper;

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
            throw new RuntimeException("Error processing message: " + e.getMessage(), e);
        }
    }
}
