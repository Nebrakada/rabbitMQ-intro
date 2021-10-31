package com.rabbitmq.app.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final AmqpAdmin amqpAdmin;

    public RabbitConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void createSchema() {
        FanoutExchange fanoutExchange = new FanoutExchange("x.fanout", true, false, null);
        Queue salesQueue = new Queue("q.sales", true, false, false);
        Binding salesBinding = new Binding(salesQueue.getName(), Binding.DestinationType.QUEUE, fanoutExchange.getName(), "", null);

        Queue logsQueue = QueueBuilder
                .durable("q.sales")
                .build();


        amqpAdmin.declareQueue(salesQueue);
        amqpAdmin.declareQueue(logsQueue);

        amqpAdmin.declareExchange(fanoutExchange);

        amqpAdmin.declareBinding(salesBinding);
    }
}
