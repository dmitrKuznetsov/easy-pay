package com.github.dmitrkuznetsov.payment.service;

import com.github.dmitrkuznetsov.payment.entity.Payment;

public interface KafkaProducerService {
    void dataHandler(Payment value);
}
