package com.pooranjoyb.product.service.messaging;

import com.pooranjoyb.product.service.product.service.ProductService;
import com.pooranjoyb.shared.contracts.OrderCreatedEvent;
import com.pooranjoyb.shared.messaging.QueueNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = QueueNames.ORDER_CREATED_QUEUE)
public class OrderEventConsumer {

    private final ProductService productService;

    @RabbitHandler
    public void consumeOrderEvent(OrderCreatedEvent orderCreatedEvent) {
        log.debug("Order-service Consumer invoked");
        productService.consumeOrder(orderCreatedEvent);
        log.debug("Order consumed : {}", orderCreatedEvent);
    }
}
