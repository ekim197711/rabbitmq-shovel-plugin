package com.example.demo.space;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CreateRabbitStuff {
    private final RabbitTemplate rabbit;
    private final RabbitAdmin admin;

    @PostConstruct
    public void createStuff(){
        Queue queueMoon = new Queue("Moon", true);
        Queue queueMars = new Queue("Mars", true);
        admin.declareQueue(queueMars);
        admin.declareQueue(queueMoon);

        Exchange toMoon = ExchangeBuilder.directExchange("ToMoon").build();
        admin.declareExchange(toMoon);
        Binding binding = new Binding("Mars", Binding.DestinationType.QUEUE, toMoon.getName(),
                "",
                new HashMap<>());
        admin.declareBinding(binding);
    }
}
