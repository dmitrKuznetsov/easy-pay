package com.github.dmitrkuznetsov.backend.listener;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import com.github.dmitrkuznetsov.backend.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentKafkaListener implements MessageListener<Long, Payment> {

    private final PaymentService paymentService;

    public PaymentKafkaListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void onMessage(ConsumerRecord<Long, Payment> record) {
        paymentService.save(record.value());
    }
}
