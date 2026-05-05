package com.pooranjoyb.order.service.messaging;

import com.pooranjoyb.order.service.core.config.RabbitMQConfig;
import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.order.service.order.repository.OrderRepository;
import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.shared.common.ProductEventType;
import com.pooranjoyb.shared.contracts.ProductReservationResultEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductReservationEventConsumer {

    private final OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_RESERVATION_RESULT_QUEUE)
    public void consumeProductReservationResult(ProductReservationResultEvent event) {
        log.info("Received ProductReservationResultEvent: {}", event);
        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + event.getOrderId()));
        if (event.getProductEventType() == ProductEventType.PRODUCT_RESERVED) {
            order.setStatus(OrderStatus.BOOKED);
            log.info("Order {} marked as BOOKED", order.getId());
        } else if (event.getProductEventType() == ProductEventType.PRODUCT_REJECTED) {
            order.setStatus(OrderStatus.FAILED);
            log.info("Order {} marked as FAILED. Reason: {}", order.getId(), event.getReason());
        }
        orderRepository.save(order);
        log.info("Order {} status updated to {}", order.getId(), order.getStatus());
    }
}
