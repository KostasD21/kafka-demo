package com.example.kafkademo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = "${kafka.message.topic}", groupId = "foo")
    public void listenGroupFoo(String message) {
        logger.info("Received Message in group foo: {}", message);
    }
}
