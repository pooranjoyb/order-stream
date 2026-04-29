package com.pooranjoyb.product.service.messaging;

import com.pooranjoyb.shared.contracts.ProductReservationResultEvent;
import com.pooranjoyb.shared.messaging.ExchangeNames;
import com.pooranjoyb.shared.messaging.RoutingKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReservationEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishProductReservationEvent(ProductReservationResultEvent productReservationResultEvent) {
        rabbitTemplate.convertAndSend(ExchangeNames.ORDER_EXCHANGE, RoutingKeys.PRODUCT_RESERVED, productReservationResultEvent);
    }
}
