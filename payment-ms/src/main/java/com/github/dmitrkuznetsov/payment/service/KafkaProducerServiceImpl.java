package com.github.dmitrkuznetsov.payment.service;

import com.github.dmitrkuznetsov.payment.dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.github.dmitrkuznetsov.payment.config.KafkaTopicConfig.TO_BACKEND;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<Long, Payment> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<Long, Payment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Payment payment) {
        log.info("Sending payment: {}", payment);
        kafkaTemplate.send(TO_BACKEND, payment);
    }
}
