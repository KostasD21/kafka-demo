package com.example.kafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    @KafkaListener(topics = "test-topic", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
