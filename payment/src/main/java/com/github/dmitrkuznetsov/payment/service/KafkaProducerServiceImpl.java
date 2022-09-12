package com.github.dmitrkuznetsov.payment.service;

import com.github.dmitrkuznetsov.payment.entity.Payment;
import com.github.dmitrkuznetsov.payment.kafka.MyKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import static com.github.dmitrkuznetsov.payment.kafka.MyKafkaProducer.TOPIC_NAME;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final MyKafkaProducer myKafkaProducer;
    private final PaymentHandlerService paymentHandlerService;

    public KafkaProducerServiceImpl(MyKafkaProducer myKafkaProducer, PaymentHandlerService paymentHandlerService) {
        this.paymentHandlerService = paymentHandlerService;
        this.myKafkaProducer = myKafkaProducer;
    }

    @Override
    public void dataHandler(Payment value) {
        log.info("value:{}", value);
        try {
            myKafkaProducer.getMyProducer().send(new ProducerRecord<>(TOPIC_NAME, value.accountId(), value),
                    (metadata, exception) -> {
                        if (exception != null) {
                            log.error("message wasn't sent", exception);
                        } else {
                            log.info("message id:{} was sent, offset:{}", value.accountId(), metadata.offset());
                            paymentHandlerService.accept(value);
                        }
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
