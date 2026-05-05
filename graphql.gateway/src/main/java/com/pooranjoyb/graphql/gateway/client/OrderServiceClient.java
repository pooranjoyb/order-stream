package com.pooranjoyb.graphql.gateway.client;

import com.pooranjoyb.graphql.gateway.core.Constants;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {

    private final BaseWebClient baseWebClient;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public List<OrderDto> getOrders() {
        return baseWebClient.getList(orderServiceUrl, Constants.ORDER_GET_ORDERS, OrderDto.class);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        return baseWebClient.postObject(orderServiceUrl, Constants.ORDER_CREATE_ORDER, orderDto, OrderDto.class);
    }
}