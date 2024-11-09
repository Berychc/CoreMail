package com.example.mail.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация RabbitMQ для обработки сообщений почтового сервиса.
 * Настраивает очередь, шаблон RabbitTemplate и администратор AMQP.
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    /**
     * Создает очередь с именем "mail".
     *
     * @return Объект очереди, который будет использоваться для отправки и получения сообщений.
     */
    @Bean
    public Queue mailQueue() {
        return new Queue("mail", false);
    }

    /**
     * Создает и настраивает RabbitTemplate для отправки сообщений.
     *
     * @param connectionFactory Фабрика соединений для RabbitMQ.
     * @param objectMapper      Объект ObjectMapper для преобразования сообщений в формат JSON.
     * @return Настроенный RabbitTemplate.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }

    /**
     * Создает фабрику соединений с RabbitMQ.
     *
     * @return Фабрика соединений к RabbitMQ, настроенная для подключения к "localhost".
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    /**
     * Создает административный интерфейс для работы с AMQP.
     *
     * @return Объект AmqpAdmin для управления очередями и другими объектами AMQP.
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
}
