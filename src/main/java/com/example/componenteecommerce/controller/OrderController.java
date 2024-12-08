package com.example.componenteecommerce.controller;

import com.example.componenteecommerce.dto.OrderDTO;
import com.example.componenteecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OrderDTO create(@RequestBody OrderDTO order) {
        return orderService.create(order);
    }

}
