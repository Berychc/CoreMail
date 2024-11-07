package com.example.mail.controller;

import com.example.mail.model.Event;
import com.example.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService service;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody Event event) {
        try {
            // Отправляем событие через RabbitMQ
            service.sendMail(event);
            return ResponseEntity.ok("Mail sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending mail: " + e.getMessage());
        }
    }
}
