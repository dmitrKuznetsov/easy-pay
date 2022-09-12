package com.github.dmitrkuznetsov.backend.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "id")
    private long id;
    private long accountId;
    private double amount;

    public Payment(long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
