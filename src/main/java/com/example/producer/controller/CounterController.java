package com.example.producer.controller;

import com.example.producer.service.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("counter")
public class CounterController {

    @GetMapping
    public long get() {
       return Counter.get();
    }

    @PostMapping
    public void increment() {
        Counter.increment();
    }

    @DeleteMapping
    public void reset() {
        Counter.reset();
    }

}
