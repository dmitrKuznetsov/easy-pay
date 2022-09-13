package com.github.dmitrkuznetsov.payment.service;

import com.github.dmitrkuznetsov.payment.dto.Payment;

public interface KafkaProducerService {
    void dataHandler(Payment value);
}
