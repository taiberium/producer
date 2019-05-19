package com.example.producer.controller;

import com.example.producer.service.Counter;
import com.example.producer.service.PositionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("producer")
public class MessageProducerController {

    private final PositionProducer positionProducer;

    @PostMapping
    public void produceMessages(
            @RequestParam(defaultValue = "4") int threadsAmount,
            @RequestParam(defaultValue = "1000") int messagesAmount
    ) {
        Counter.reset();
        positionProducer.send(threadsAmount, messagesAmount);
    }
}
