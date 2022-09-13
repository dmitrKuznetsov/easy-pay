package com.github.dmitrkuznetsov.payment.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dmitrkuznetsov.payment.dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Properties;

import static com.github.dmitrkuznetsov.payment.kafka.JsonSerializer.OBJECT_MAPPER;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Component
@Slf4j
public class MyKafkaProducer {
    private final KafkaProducer<Long, Payment> kafkaProducer;
    public static final String TOPIC_NAME = "MyTopic";

    public MyKafkaProducer(@Value("${kafka.bootstrap.servers}") String bootstrapServers)  {
        Properties props = new Properties();
        props.put(CLIENT_ID_CONFIG, "myKafkaProducer");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ACKS_CONFIG, "1");
        props.put(RETRIES_CONFIG, 1);
        props.put(BATCH_SIZE_CONFIG, 0);
        props.put(LINGER_MS_CONFIG, 10);
        props.put(BUFFER_MEMORY_CONFIG, 33_554_432); //bytes
        props.put(MAX_BLOCK_MS_CONFIG, 1_000); //ms
        props.put(KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(OBJECT_MAPPER, new ObjectMapper());

        kafkaProducer = new KafkaProducer<>(props);

        var shutdownHook = new Thread(() -> {
            log.info("closing kafka producer");
            kafkaProducer.close();
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public KafkaProducer<Long, Payment> getMyProducer() {
        return kafkaProducer;
    }

}
