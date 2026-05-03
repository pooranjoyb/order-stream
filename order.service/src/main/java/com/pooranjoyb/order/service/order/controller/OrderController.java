package com.pooranjoyb.order.service.order.controller;

import com.pooranjoyb.order.service.order.dto.OrderRequestDto;
import com.pooranjoyb.order.service.order.dto.OrderResponseDto;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        List<Order> orders = orderService.getOrders();

        List<OrderResponseDto> responses = orders.stream()
                .map(this::convertToOrderResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        Order order = convertToOrder(requestDto);
        Order saved = orderService.createOrder(order);

        OrderResponseDto response = convertToOrderResponseDto(saved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        OrderResponseDto response = convertToOrderResponseDto(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Request DTO → Entity
    private Order convertToOrder(OrderRequestDto request) {
        if (request == null) return null;

        Order order = new Order();
        order.setPrice(request.getPrice());
        order.setCategory(request.getCategory());
        order.setItem(request.getItem());
        order.setQuantity(request.getQuantity());

        return order;
    }

    // ✅ Entity → Response DTO
    private OrderResponseDto convertToOrderResponseDto(Order order) {
        if (order == null) return null;

        return OrderResponseDto.builder()
                .item(order.getItem())
                .category(order.getCategory())
                .price(order.getPrice())       // BigDecimal → BigDecimal (no conversion)
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .netAmount(order.getNetAmount())
                .build();
    }
}