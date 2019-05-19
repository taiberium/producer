package com.example.producer.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PositionBinding {

    String POSITIONS = "positions";

    @Output(POSITIONS)
    MessageChannel positions();
}
