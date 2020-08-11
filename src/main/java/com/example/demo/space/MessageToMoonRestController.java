package com.example.demo.space;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageToMoonRestController {
    private final RabbitTemplate rabbit;
    @PostMapping("/moon")
    public String sendMessageToMoon(@RequestBody String message){
        rabbit.send("ToMoon", "", new Message(message.getBytes(), new MessageProperties()));
        return "Message has been sent to the moon exchange: " + message;
    }
}
