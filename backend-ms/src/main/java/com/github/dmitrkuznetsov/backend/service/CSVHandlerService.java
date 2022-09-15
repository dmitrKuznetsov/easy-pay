package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.entity.Payment;

import java.util.List;

public interface CSVHandlerService {

    void savePayments(List<Payment> payments);
}
