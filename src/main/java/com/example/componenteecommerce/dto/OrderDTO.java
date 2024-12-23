package com.example.componenteecommerce.dto;

import com.example.componenteecommerce.entity.User;
import com.example.componenteecommerce.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID id;
    @NotNull
    private User user;
    @NotNull
    private List<ItemDTO> items;
    private OrderStatus orderStatus = OrderStatus.PENDING;
    private String payment;

}
