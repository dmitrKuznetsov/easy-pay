package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.dao.PaymentDAO;
import com.github.dmitrkuznetsov.backend.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public Payment save(Payment payment) {
        paymentDAO.save(payment);
        log.info("Saved payment: {}", payment);
        return payment;
    }
}
