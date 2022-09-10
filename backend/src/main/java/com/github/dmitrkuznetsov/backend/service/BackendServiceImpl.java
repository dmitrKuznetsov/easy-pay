package com.github.dmitrkuznetsov.backend.service;

import org.springframework.stereotype.Service;

@Service
public class BackendServiceImpl implements BackendService {
    public void run() {
        System.out.println("Service run");
    }
}
