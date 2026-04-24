package com.pooranjoyb.order_stream.order.service.impl;

import com.pooranjoyb.order_stream.order.common.enums.OrderStatus;
import com.pooranjoyb.order_stream.order.dto.OrderRequestDto;
import com.pooranjoyb.order_stream.order.entity.Order;
import com.pooranjoyb.order_stream.order.repository.OrderRepository;
import com.pooranjoyb.order_stream.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        order.setItem(orderRequestDto.getItem());
        order.setStatus(OrderStatus.PENDING);
        order.setPrice(orderRequestDto.getPrice());
        order.setCategory(orderRequestDto.getCategory());
        order.setQuantity(orderRequestDto.getQuantity());

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + id));
    }
}
