package com.pooranjoyb.order.service.core.config;

import com.pooranjoyb.shared.messaging.ExchangeNames;
import com.pooranjoyb.shared.messaging.RoutingKeys;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_RESERVATION_RESULT_QUEUE = "product.reservation.result.queue";

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue productReservationResultQueue() {
        return new Queue(PRODUCT_RESERVATION_RESULT_QUEUE, true);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ExchangeNames.ORDER_EXCHANGE);
    }

    @Bean
    public Binding productReservationResultBinding(
            Queue productReservationResultQueue,
            TopicExchange orderExchange
    ) {
        return BindingBuilder
                .bind(productReservationResultQueue)
                .to(orderExchange)
                .with(RoutingKeys.PRODUCT_RESERVED);
    }
}