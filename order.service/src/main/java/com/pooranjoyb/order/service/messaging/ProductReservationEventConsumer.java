package com.pooranjoyb.order.service.messaging;

import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.shared.common.ProductEventType;
import com.pooranjoyb.shared.contracts.ProductReservationResultEvent;
import com.pooranjoyb.shared.messaging.QueueNames;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = QueueNames.PRODUCT_RESERVED_QUEUE)
public class ProductReservationEventConsumer {

    private final OrderRepository orderRepository;

    @RabbitHandler
    public void consumeProductReservationResult(ProductReservationResultEvent event) {
        log.info("Received ProductReservationResultEvent: {}", event);
        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + event.getOrderId()));
        if (Objects.equals(event.getProductEventType(), ProductEventType.PRODUCT_RESERVED)) {
            order.setStatus(OrderStatus.BOOKED);
            log.info("Order {} marked as BOOKED", order.getId());
        } else if (Objects.equals(event.getProductEventType(), ProductEventType.PRODUCT_REJECTED)) {
            order.setStatus(OrderStatus.FAILED);
            log.info("Order {} marked as FAILED. Reason: {}", order.getId(), event.getReason());
        }
        orderRepository.save(order);
        log.info("Order {} status updated to {}", order.getId(), order.getStatus());
    }
}
