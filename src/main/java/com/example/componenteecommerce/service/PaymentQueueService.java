package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.PaymentDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentQueueService {

    private final String EXCHANGE = "pag-exchange";
    private final String ROUTING_KEY = "pag.req.credit";

    private final AmqpTemplate amqpTemplate;

    public PaymentQueueService(@Qualifier("rabbitTemplate") AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendPaymentToQueue(PaymentDTO paymentDTO) {

        amqpTemplate.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                paymentDTO
        );

    }

}
