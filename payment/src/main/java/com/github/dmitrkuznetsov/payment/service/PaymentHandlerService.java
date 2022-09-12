package com.github.dmitrkuznetsov.payment.service;


import com.github.dmitrkuznetsov.payment.entity.Payment;

import java.util.function.Consumer;

public interface PaymentHandlerService extends Consumer<Payment> {
}
