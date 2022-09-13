package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.dao.PaymentDAO;
import com.github.dmitrkuznetsov.backend.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PaymentHandlerServiceToMySql implements PaymentHandlerService {
    private final PaymentDAO paymentDAO;

    public PaymentHandlerServiceToMySql(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public void accept(Payment payment) {
        paymentDAO.save(payment);
        log.info("Saved payment: {}", payment);
    }
}
