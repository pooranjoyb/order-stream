package com.pooranjoyb.order.service.order.service.impl;

import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.order.service.order.dto.OrderRequestDto;
import com.pooranjoyb.order.service.order.dto.OrderResponseDto;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.shared.common.OrderEventType;
import com.pooranjoyb.shared.contracts.OrderEvent;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.order.service.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

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
        publishOrderEvent(savedOrder);
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

    private void publishOrderEvent(Order order) {
        OrderEvent orderEvent = OrderEvent.builder()
                .orderId(order.getId())
                .item(order.getItem())
                .category(order.getCategory())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .netAmount(order.getNetAmount())
                .status(order.getStatus())
                .createdAt(LocalDateTime.now())
                .eventType(OrderEventType.ORDER_CREATED)
                .build();

        rabbitTemplate.convertAndSend("order.exchange", "order.created", orderEvent);
    }
}
