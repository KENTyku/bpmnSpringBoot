package com.camundaspringboot.demo.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class KafkaService {
    private final Logger logger = getLogger(KafkaService.class);
    @Value(value = "${kafka.topicName}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
        logger.info("Message is sent");
    }
}
