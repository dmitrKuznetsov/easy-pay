package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.entity.Payment;

import java.util.function.Consumer;

public interface PaymentHandlerService extends Consumer<Payment> {
}
