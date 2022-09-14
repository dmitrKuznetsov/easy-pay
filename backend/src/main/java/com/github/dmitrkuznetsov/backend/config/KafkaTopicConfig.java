package com.github.dmitrkuznetsov.backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TO_BACKEND = "tobackend";

    @Bean
    public NewTopic tobackendTopic() {
        return TopicBuilder.name(TO_BACKEND).build();
    }
}
