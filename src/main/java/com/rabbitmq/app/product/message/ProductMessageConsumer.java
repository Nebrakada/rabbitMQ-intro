package com.rabbitmq.app.product.message;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.app.model.Product;

@Service
@RabbitListener(queues = "q.sales")
public class ProductMessageConsumer {

    @RabbitHandler
    public void getProductCreated(Product product) {
        System.out.println("received message: " + product);
    }

    @RabbitHandler(isDefault = true)
    public void getDefault(Object object) {
        System.out.println("received default: " + object);
    }
}
