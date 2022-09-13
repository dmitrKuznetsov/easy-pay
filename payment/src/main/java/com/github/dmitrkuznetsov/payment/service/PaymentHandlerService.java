package com.github.dmitrkuznetsov.payment.service;


import com.github.dmitrkuznetsov.payment.dto.Payment;

import java.util.function.Consumer;

public interface PaymentHandlerService extends Consumer<Payment> {
}
