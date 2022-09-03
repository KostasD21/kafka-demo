package com.example.kafkademo.config;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

//@Configuration
//public class KafkaStreamsConfig {
//
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Value(value = "{spring.kafka.streams.state-dir}")
//    private String stateStoreLocation;
//
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    KafkaStreamsConfiguration kStreamsConfig() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(APPLICATION_ID_CONFIG, "streams-app");
//        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
//        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(STATE_DIR_CONFIG, stateStoreLocation);
//
//        return new KafkaStreamsConfiguration(props);
//    }
//}
