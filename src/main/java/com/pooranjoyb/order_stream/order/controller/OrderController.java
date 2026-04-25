package com.pooranjoyb.order_stream.order.controller;

import com.pooranjoyb.order_stream.order.dto.OrderRequestDto;
import com.pooranjoyb.order_stream.order.entity.Order;
import com.pooranjoyb.order_stream.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        Order order = orderService.createOrder(requestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
