package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.PaymentDTO;
import com.example.componenteecommerce.dto.PaymentResponseDTO;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Service
public class PaymentQueueService {

    private final String EXCHANGE = "pag-exchange";
    private final String ROUTING_KEY = "pag.req.credit";

    private final AmqpTemplate amqpTemplate;
    @Setter
    private Consumer<PaymentResponseDTO> paymentDTOConsumer;

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


    @RabbitListener(queues = {"pag-res"})
    public void receivePaymentResult(PaymentResponseDTO paymentDTO) {
        paymentDTOConsumer.accept(paymentDTO);
    }

}
