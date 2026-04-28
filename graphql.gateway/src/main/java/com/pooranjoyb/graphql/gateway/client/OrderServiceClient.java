package com.pooranjoyb.graphql.gateway.client;

import com.pooranjoyb.graphql.gateway.core.Constants;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceClient  {

    private final BaseWebClient baseWebClient;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public List<OrderDto> getOrders() {
        return baseWebClient.getList(orderServiceUrl, Constants.ORDER_GET_ORDERS, OrderDto.class);
    }
}
