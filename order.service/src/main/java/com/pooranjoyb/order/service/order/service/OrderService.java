package com.pooranjoyb.order.service.order.service;

import com.pooranjoyb.order.service.order.dto.OrderRequestDto;
import com.pooranjoyb.order.service.order.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getOrders();
    OrderResponseDto getOrderById(Long id);
    void deleteOrderById(Long id);
}