package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.dao.PaymentDAO;
import com.github.dmitrkuznetsov.backend.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final CSVHandlerService csvHandlerService;
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

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        csvHandlerService.saveNewPayments();
    }
}
