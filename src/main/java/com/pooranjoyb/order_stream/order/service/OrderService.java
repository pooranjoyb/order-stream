package com.pooranjoyb.order_stream.order.service;

import com.pooranjoyb.order_stream.order.dto.OrderRequestDto;
import com.pooranjoyb.order_stream.order.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequestDto orderRequestDto);
}
