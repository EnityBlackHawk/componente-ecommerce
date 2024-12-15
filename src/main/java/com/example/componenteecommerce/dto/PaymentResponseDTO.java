package com.example.componenteecommerce.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {

    private String id;
    private UUID orderId;
    @CreditCardNumber(message = "Invalid card number")
    private String cardNumber;
    private Integer cvv;
    private String expirationDate;

}
