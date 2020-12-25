package com.camundaspringboot.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.slf4j.LoggerFactory.getLogger;

public class JavaKafkaProducer {
    static private final Logger logger = getLogger(JavaKafkaProducer.class);

    static String server = "localhost:9092";
    static String topicName = "test.t.svfc.incident.claim.receiver.event";

    static public void sendMessage(String message) throws ExecutionException, InterruptedException {

        final Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        final Producer<Long, String> producer =
                new KafkaProducer<>(props);

        RecordMetadata recordMetadata = (RecordMetadata) producer
                .send(new ProducerRecord(topicName, message)).get();
        logger.info("Message is sent");
        producer.close();
    }
}
