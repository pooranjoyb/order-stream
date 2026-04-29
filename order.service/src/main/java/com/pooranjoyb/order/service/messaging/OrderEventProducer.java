package com.pooranjoyb.order.service.messaging;

import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.shared.common.OrderEventType;
import com.pooranjoyb.shared.contracts.OrderCreatedEvent;
import com.pooranjoyb.shared.messaging.ExchangeNames;
import com.pooranjoyb.shared.messaging.RoutingKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void publishOrderEvent(Order order) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(order.getId())
                .item(order.getItem())
                .quantity(order.getQuantity())
                .createdAt(LocalDateTime.now())
                .eventType(OrderEventType.ORDER_CREATED)
                .build();

        rabbitTemplate.convertAndSend(ExchangeNames.ORDER_EXCHANGE, RoutingKeys.ORDER_CREATED, orderCreatedEvent);
    }
}
