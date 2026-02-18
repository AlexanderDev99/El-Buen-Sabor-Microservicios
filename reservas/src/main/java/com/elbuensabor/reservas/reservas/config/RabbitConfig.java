package com.elbuensabor.reservas.reservas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "audit_queue";

    @Bean
    public Queue queue() {
        // durable: true para que la cola sobreviva a reinicios del servidor
        return new Queue(QUEUE_NAME, true);
    }
}