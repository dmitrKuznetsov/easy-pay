package com.github.dmitrkuznetsov.backend.dao;

import com.github.dmitrkuznetsov.backend.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentDAO {

    void save(Payment payment);
    List<Payment> getPaymentFromDate(Date date);
}
