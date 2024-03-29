package com.example.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.message.topic}")
    private String topic;

    @Bean
    public NewTopic testTopic() {
        return new NewTopic(topic, 3, (short) 1);
    }
}