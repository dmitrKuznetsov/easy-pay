package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.dao.PaymentDAO;
import com.github.dmitrkuznetsov.backend.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentHandlerServiceToMySql implements PaymentHandlerService {
    private final PaymentDAO paymentDAO;

    public PaymentHandlerServiceToMySql(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    @Transactional
    public void accept(Payment payment) {
        paymentDAO.save(payment);
    }
}
