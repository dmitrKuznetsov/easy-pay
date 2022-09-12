package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import com.github.dmitrkuznetsov.backend.kafka.MyKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.github.dmitrkuznetsov.backend.kafka.MyKafkaConsumer.MAX_POLL_INTERVAL_MS;

@Service
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final MyKafkaConsumer myKafkaConsumer;
    private final Duration timeout = Duration.ofMillis(2_000);
    private final PaymentHandlerService paymentHandlerService;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public KafkaConsumerServiceImpl(MyKafkaConsumer myKafkaConsumer, PaymentHandlerService paymentHandlerService) {
        this.myKafkaConsumer = myKafkaConsumer;
        this.paymentHandlerService = paymentHandlerService;
    }

    public void start() {
        executor.scheduleAtFixedRate(this::poll, 0, MAX_POLL_INTERVAL_MS * 2L, TimeUnit.MILLISECONDS);
    }

    private void poll() {
        log.info("poll records");
        ConsumerRecords<Long, Payment> records = myKafkaConsumer.getConsumer().poll(timeout);
//         sleep();
        log.info("polled records.counter:{}", records.count());
        for (ConsumerRecord<Long, Payment> kafkaRecord : records) {
            try {
                var key = kafkaRecord.key();
                var value = kafkaRecord.value();
                log.info("key:{}, value:{}, record:{}", key, value, kafkaRecord);
                paymentHandlerService.accept(value);
            } catch (Exception ex) {
                log.error("can't parse record:{}", kafkaRecord, ex);
            }
        }
    }

    public void stop() {
        executor.shutdown();
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
