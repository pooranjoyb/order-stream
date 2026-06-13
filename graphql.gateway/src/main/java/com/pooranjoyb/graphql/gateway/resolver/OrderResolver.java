package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.client.OrderServiceClient;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import com.pooranjoyb.graphql.gateway.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderResolver {

    private final OrderServiceClient orderServiceClient;

    @QueryMapping
    public List<OrderDto> getOrders() {
        return orderServiceClient.getOrders();
    }

    @QueryMapping
    public OrderDto getOrder(@Argument Long id) {
        return orderServiceClient.getOrderById(id);
    }

    @MutationMapping
    public OrderDto createOrder(@Argument("order") OrderRequestDto orderRequestDto) {
        return orderServiceClient.createOrder(orderRequestDto);
    }

    @MutationMapping
    public OrderDto updateOrder(@Argument Long id, @Argument("order") OrderRequestDto orderRequestDto) {
        return orderServiceClient.updateOrder(id, orderRequestDto);
    }

    @MutationMapping
    public Boolean deleteOrder(@Argument Long id) {
        orderServiceClient.deleteOrder(id);
        return true;
    }
}
