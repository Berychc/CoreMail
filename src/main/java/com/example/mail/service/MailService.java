package com.example.mail.service;

import com.example.mail.model.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    @RabbitListener(queues = "mail")
    public void sendMail(Event event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("Уведомление");
        message.setText(event.getDescription());
        sender.send(message);
    }
}
