package org.lkwangus.demo.restjpa.backend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.template.exchangePrice}")
    public String QUEUE_NAME = "priceQueue";

    @Value("${spring.rabbitmq.template.queuePrice}")
    public String EXCHANGE_NAME;

    @Bean
    public Queue priceQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
}
