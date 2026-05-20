package com.pooranjoyb.graphql.gateway.client;

import com.pooranjoyb.graphql.gateway.core.Constants;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import com.pooranjoyb.graphql.gateway.dto.OrderRequestDto;
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

    public OrderDto getOrderById(Long id) {
        String uri = Constants.ORDER_GET_BY_ID.replace("{id}", id.toString());
        return baseWebClient.getObject(orderServiceUrl, uri, OrderDto.class);
    }

    public OrderDto createOrder(OrderRequestDto requestDto) {
        return baseWebClient.post(orderServiceUrl, Constants.ORDER_CREATE, requestDto, OrderDto.class);
    }

    public OrderDto updateOrder(Long id, OrderRequestDto requestDto) {
        String uri = Constants.ORDER_UPDATE_BY_ID.replace("{id}", id.toString());
        return baseWebClient.put(orderServiceUrl, uri, requestDto, OrderDto.class);
    }

    public void deleteOrder(Long id) {
        String uri = Constants.ORDER_DELETE_BY_ID.replace("{id}", id.toString());
        baseWebClient.delete(orderServiceUrl, uri);
    }
}
