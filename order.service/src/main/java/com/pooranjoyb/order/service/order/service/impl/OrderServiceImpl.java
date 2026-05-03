package com.pooranjoyb.order.service.order.service.impl;

import com.pooranjoyb.order.service.messaging.OrderEventProducer;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.order.service.order.service.OrderService;
import com.pooranjoyb.shared.common.OrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    @Override
    public Order createOrder(Order order) {
        if (order.getPrice() != null && order.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }
        if (order.getQuantity() != null && order.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
        }

        Order savedOrder = orderRepository.save(order);

        orderEventProducer.publishOrderEvent(savedOrder);
        log.debug("Order Published in queue: {}", savedOrder);

        return savedOrder;
    }

    @Override
    public java.util.List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    @Override
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }


}
