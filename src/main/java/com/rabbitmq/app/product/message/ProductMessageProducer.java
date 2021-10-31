package com.rabbitmq.app.product.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.rabbitmq.app.model.Product;

@Service
public class ProductMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ProductMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Product product) {
        rabbitTemplate.convertAndSend("exchange", "routing key", product);
    }
}
