package com.example.componenteecommerce.controller;

import com.example.componenteecommerce.dto.OrderDTO;
import com.example.componenteecommerce.dto.create.CreateOrderDTO;
import com.example.componenteecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${endpoint.prefix}/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAll() {
        return orderService.findAll();
    }

    @PostMapping("/create")
    public OrderDTO create(@RequestBody @Valid CreateOrderDTO order) {
        return orderService.create(order);
    }

    @GetMapping("/get/{id}")
    public OrderDTO get(@PathVariable UUID id) {
        return orderService.findById(id);
    }

}
