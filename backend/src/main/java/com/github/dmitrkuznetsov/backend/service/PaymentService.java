package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.entity.Payment;

public interface PaymentService {
    Payment save(Payment payment);
}
