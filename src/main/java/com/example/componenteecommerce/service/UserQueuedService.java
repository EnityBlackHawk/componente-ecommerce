package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.PaymentResponseDTO;
import com.example.componenteecommerce.dto.create.CreateUserDTO;
import com.example.componenteecommerce.dto.create.ReplicateUserDTO;
import com.example.componenteecommerce.entity.User;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserQueuedService {

    private final String EXCHANGE = "auth-exchange";
    private final String ROUTING_KEY = "auth.user.create";

    private final AmqpTemplate amqpTemplate;
    @Setter
    private Consumer<PaymentResponseDTO> paymentDTOConsumer;

    public UserQueuedService(@Qualifier("rabbitTemplate") AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendUser(ReplicateUserDTO user) {

        amqpTemplate.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                user
        );

    }


}
