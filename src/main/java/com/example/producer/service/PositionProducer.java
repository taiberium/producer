package com.example.producer.service;

import com.example.producer.config.PositionBinding;
import com.example.producer.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionProducer {

    private final PositionBinding positionBinding;

    private void send(Message<List<Position>> message) {
        Counter.increment();
        positionBinding.positions().send(message);
    }

    public void send(int threadsAmount, int messagesAmount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);

        log.info("started");

        IntStream.range(0, threadsAmount)
                .forEach(threadNumber -> {
                            log.info("Make thread task:{}", threadNumber);
                            var messages = makeMessages(
                                    messagesAmount * threadNumber,
                                    messagesAmount * (threadNumber + 1));
                            executorService.execute(() -> messages.forEach(this::send));
                        }
                );

        log.info("finished");
    }

    private List<Message> makeMessages(int accountStart, int accountEnd) {
        return IntStream.range(accountStart, accountEnd)
                .mapToObj(account -> MessageBuilder.withPayload(make10Positions(account)).build())
                .collect(Collectors.toList());
    }

    private List<Position> make10Positions(int account) {
        return IntStream.range(0, 10)
                .mapToObj(isin -> makePosition(account, isin))
                .collect(Collectors.toList());
    }

    private Position makePosition(int account, int isin) {
        return Position.builder()
                .account(String.valueOf(account))
                .isin(String.valueOf(isin))
                .t0(BigDecimal.valueOf(200))
                .t0(BigDecimal.valueOf(300))
                .build();
    }
}
