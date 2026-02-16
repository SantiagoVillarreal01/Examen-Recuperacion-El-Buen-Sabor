package com.elbuensabor.reservas.reservas.controllers.rabbit;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RabbitMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Value("${spring.application.name}")
    private String appName;

    public void sendLog(String endpoint) {
        String message = String.format("MS: [%s] -> Endpoint llamado: [%s] -> Hora: %s", 
                          appName, endpoint, LocalDateTime.now());
        rabbitTemplate.convertAndSend(queue, message);
    }
}
