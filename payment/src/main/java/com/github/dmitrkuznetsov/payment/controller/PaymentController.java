package com.github.dmitrkuznetsov.payment.controller;

import com.github.dmitrkuznetsov.payment.dto.Payment;
import com.github.dmitrkuznetsov.payment.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    private final KafkaProducerService kafkaProducerService;
    public PaymentController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public void addNewPayment(@RequestBody Payment payment) {
        kafkaProducerService.dataHandler(payment);
    }
}
