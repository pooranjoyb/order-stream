package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.client.OrderServiceClient;
import com.pooranjoyb.graphql.gateway.dto.OrderDto;
import com.pooranjoyb.graphql.gateway.dto.OrderRequestDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderResolverTests {

    private final OrderServiceClient orderServiceClient = mock(OrderServiceClient.class);
    private final OrderResolver orderResolver = new OrderResolver(orderServiceClient);

    @Test
    void getOrdersDelegatesToOrderServiceClient() {
        OrderDto order = OrderDto.builder().id(1L).item("keyboard").build();
        when(orderServiceClient.getOrders()).thenReturn(List.of(order));

        List<OrderDto> orders = orderResolver.getOrders();

        assertThat(orders).containsExactly(order);
        verify(orderServiceClient).getOrders();
    }

    @Test
    void getOrderDelegatesToOrderServiceClient() {
        OrderDto order = OrderDto.builder().id(12L).item("monitor").build();
        when(orderServiceClient.getOrderById(12L)).thenReturn(order);

        assertThat(orderResolver.getOrder(12L)).isEqualTo(order);
        verify(orderServiceClient).getOrderById(12L);
    }

    @Test
    void createOrderDelegatesToOrderServiceClient() {
        OrderRequestDto request = OrderRequestDto.builder()
                .price(BigDecimal.valueOf(1200))
                .category("electronics")
                .item("monitor")
                .quantity(1)
                .build();
        OrderDto createdOrder = OrderDto.builder().id(7L).item("monitor").build();
        when(orderServiceClient.createOrder(request)).thenReturn(createdOrder);

        assertThat(orderResolver.createOrder(request)).isEqualTo(createdOrder);
        verify(orderServiceClient).createOrder(request);
    }

    @Test
    void updateOrderDelegatesToOrderServiceClient() {
        OrderRequestDto request = OrderRequestDto.builder()
                .price(BigDecimal.valueOf(899))
                .category("books")
                .item("architecture")
                .quantity(2)
                .build();
        OrderDto updatedOrder = OrderDto.builder().id(3L).item("architecture").build();
        when(orderServiceClient.updateOrder(3L, request)).thenReturn(updatedOrder);

        assertThat(orderResolver.updateOrder(3L, request)).isEqualTo(updatedOrder);
        verify(orderServiceClient).updateOrder(3L, request);
    }

    @Test
    void deleteOrderDelegatesToOrderServiceClient() {
        assertThat(orderResolver.deleteOrder(9L)).isTrue();
        verify(orderServiceClient).deleteOrder(9L);
    }
}
