package com.example.componenteecommerce.dto.create;

import com.example.componenteecommerce.dto.ItemDTO;
import com.example.componenteecommerce.dto.PaymentDTO;
import com.example.componenteecommerce.entity.User;
import com.example.componenteecommerce.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    private UUID id;
    private User user;
    @NotNull
    private List<ItemDTO> items;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @NotNull(message = "Payment is required")
    private PaymentDTO payment;

}
