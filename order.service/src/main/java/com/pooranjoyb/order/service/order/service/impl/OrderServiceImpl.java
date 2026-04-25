package com.pooranjoyb.order.service.order.service.impl;

import com.pooranjoyb.order.service.order.common.enums.OrderStatus;
import com.pooranjoyb.order.service.order.dto.OrderRequestDto;
import com.pooranjoyb.order.service.order.dto.OrderResponseDto;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.order.service.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        if (orderRequestDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }
        if (orderRequestDto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        Order order = Order.builder()
                .item(orderRequestDto.getItem())
                .status(OrderStatus.PENDING)
                .price(orderRequestDto.getPrice())
                .category(orderRequestDto.getCategory())
                .quantity(orderRequestDto.getQuantity()).build();

        return OrderResponseDto.fromEntity(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponse = new ArrayList<>();
        for (Order i : orders)
            orderResponse.add(OrderResponseDto.fromEntity(i));

        return orderResponse;
    }
}
