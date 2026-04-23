package com.pooranjoyb.order_stream.order.service;

import com.pooranjoyb.order_stream.order.dto.OrderRequestDto;
import com.pooranjoyb.order_stream.order.entity.Order;

public interface OrderService {

    Order createOrder(OrderRequestDto orderRequestDto);
}
