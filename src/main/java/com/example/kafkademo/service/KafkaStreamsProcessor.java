package com.example.kafkademo.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamsProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsProcessor.class);

    @Value(value = "${kafka.message.topic}")
    private String topic;

    @Autowired
    public void startProcessing(StreamsBuilder streamsBuilder) {
        streamsBuilder.globalTable(topic,
                Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as("messages")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(Serdes.String()));
        logger.info("message has been stored to the kafka streams cache!");
    }

}
