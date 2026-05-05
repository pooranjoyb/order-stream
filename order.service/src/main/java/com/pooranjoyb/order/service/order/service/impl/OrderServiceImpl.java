package com.pooranjoyb.order.service.order.service.impl;

import com.pooranjoyb.order.service.messaging.OrderEventProducer;
import com.pooranjoyb.order.service.order.dto.OrderRequestDto;
import com.pooranjoyb.order.service.order.dto.OrderResponseDto;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.order.service.order.service.OrderService;
import com.pooranjoyb.shared.common.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

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

        Order savedOrder = orderRepository.save(order);

        orderEventProducer.publishOrderEvent(savedOrder);
        log.debug("Order Published in queue: {}", savedOrder);

        return OrderResponseDto.fromEntity(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponse = new ArrayList<>();
        for (Order i : orders)
            orderResponse.add(OrderResponseDto.fromEntity(i));

        return orderResponse;
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderResponseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    @Override
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponseDto updateOrderById(Long id, OrderRequestDto orderRequestDto) {
        if (orderRequestDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }
        if (orderRequestDto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        Order updatedOrder = existingOrder.toBuilder()
            .item(orderRequestDto.getItem())
            .price(orderRequestDto.getPrice())
            .category(orderRequestDto.getCategory())
            .quantity(orderRequestDto.getQuantity()).build();

        Order savedOrder = orderRepository.save(updatedOrder);
        orderEventProducer.publishOrderEvent(savedOrder);
        log.info("Order updated successfully: {}", savedOrder.getId());
        
        return OrderResponseDto.fromEntity(savedOrder);
    }


}
