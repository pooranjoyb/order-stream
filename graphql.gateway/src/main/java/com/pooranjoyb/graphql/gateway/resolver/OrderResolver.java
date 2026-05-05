package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.client.OrderServiceClient;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
    
    private final OrderServiceClient orderServiceClient;
    
    public OrderDto createOrder(OrderDto input) {
        return orderServiceClient.createOrder(input);
    }
    
    public List<OrderDto> getOrders() {
        return orderServiceClient.getOrders();
    }
}