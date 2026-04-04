package com.pooranjoyb.order_stream.order.repository;

import com.pooranjoyb.order_stream.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
