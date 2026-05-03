package com.pooranjoyb.order.service.order.service;

import com.pooranjoyb.order.service.order.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    List<Order> getOrders();
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
}