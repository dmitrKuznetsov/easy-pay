package com.github.dmitrkuznetsov.payment.controller;

import com.github.dmitrkuznetsov.payment.entity.Payment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @PostMapping
    public void addNewPayment(@RequestBody Payment payment) {
        System.out.println(payment);
    }
}
