package com.github.dmitrkuznetsov.backend;

import com.github.dmitrkuznetsov.backend.configuration.MyConfig;
import com.github.dmitrkuznetsov.backend.service.KafkaConsumerService;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        KafkaConsumerService kafkaConsumerService = context.getBean("kafkaConsumerServiceImpl", KafkaConsumerService.class);
        kafkaConsumerService.start();

        context.close();
    }
}
