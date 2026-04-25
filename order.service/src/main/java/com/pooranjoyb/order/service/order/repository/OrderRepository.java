package com.pooranjoyb.order.service.order.repository;

import com.pooranjoyb.order.service.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
