package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.dao.PaymentDAO;
import com.github.dmitrkuznetsov.backend.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final CSVHandlerService csvHandlerService;
    private volatile Date lastDate = new Date();
    public PaymentServiceImpl(PaymentDAO paymentDAO, CSVHandlerService csvHandlerService) {
        this.paymentDAO = paymentDAO;
        this.csvHandlerService = csvHandlerService;
    }

    @Override
    public Payment save(Payment payment) {
        paymentDAO.save(payment);
        log.info("Saved payment: {}", payment);
        return payment;
    }

    @Scheduled(fixedDelay = 20000)
    public void scheduleFixedDelayTask() {
        List<Payment> paymentFromDate = paymentDAO.getPaymentFromDate(lastDate);
        lastDate = new Date();
        csvHandlerService.savePayments(paymentFromDate);
    }
}
