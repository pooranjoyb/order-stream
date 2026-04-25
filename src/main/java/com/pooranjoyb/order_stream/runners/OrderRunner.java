package com.pooranjoyb.order_stream.runners;

import com.pooranjoyb.order_stream.order.common.enums.OrderStatus;
import com.pooranjoyb.order_stream.order.entity.Order;
import com.pooranjoyb.order_stream.order.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class OrderRunner implements CommandLineRunner {

    private final OrderRepository orderRepository;

    public OrderRunner(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setPrice(new BigDecimal(12));
        order.setNetAmount(new BigDecimal(120));
        order.setCategory("Electronic");
        order.setQuantity(10);
        order.setStatus(OrderStatus.BOOKED);
        order.setItem("Headphone");
        order.setCreatedBy("pooranjoyb");
        order.setUpdatedBy("pooranjoyb");

        orderRepository.save(order);
        System.out.println("Order saved!");
    }
}
