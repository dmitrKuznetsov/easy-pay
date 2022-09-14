package com.github.dmitrkuznetsov.backend.config;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import com.github.dmitrkuznetsov.backend.listener.PaymentKafkaListener;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static com.github.dmitrkuznetsov.backend.config.KafkaTopicConfig.TO_BACKEND;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
@ComponentScan(basePackages = "com.github.dmitrkuznetsov.backend")
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired
    private PaymentKafkaListener listener;
    public static final String GROUP_ID ="myKafkaConsumerGroup";

    @Bean
    public ConsumerFactory<Long, Payment> consumerFactory() {

        JsonDeserializer<Payment> deserializer = new JsonDeserializer<>(Payment.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object>  props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(GROUP_ID_CONFIG, GROUP_ID);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new LongDeserializer(), deserializer);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(
            ConsumerFactory<Long, Payment> consumerFactory
    ) {
        ContainerProperties containerProperties = new ContainerProperties(TO_BACKEND);
        containerProperties.setMessageListener(listener);

        return new ConcurrentMessageListenerContainer<>(
                consumerFactory,
                containerProperties
        );
    }
}
