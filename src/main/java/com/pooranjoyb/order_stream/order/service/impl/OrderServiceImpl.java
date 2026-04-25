package com.pooranjoyb.order_stream.order.service.impl;

import com.pooranjoyb.order_stream.order.common.enums.OrderStatus;
import com.pooranjoyb.order_stream.order.dto.OrderRequestDto;
import com.pooranjoyb.order_stream.order.entity.Order;
import com.pooranjoyb.order_stream.order.repository.OrderRepository;
import com.pooranjoyb.order_stream.order.service.OrderService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

@Override
public Order createOrder(OrderRequestDto orderRequestDto) {
    if(orderRequestDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
        throw new RuntimeException("Price must be greater than 0");
    }
    if (orderRequestDto.getQuantity() <= 0) {
        throw new RuntimeException("Quantity must be greater than 0");
    }
        Order order = new Order();
        order.setItem(orderRequestDto.getItem());
        order.setStatus(OrderStatus.PENDING);
        order.setPrice(orderRequestDto.getPrice());
        order.setCategory(orderRequestDto.getCategory());
        order.setQuantity(orderRequestDto.getQuantity());

        return orderRepository.save(order);
    }
}
