package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.core.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class OrderResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
    
    private final ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<>();
    
    public Order createOrder(CreateOrderInput input) {
        String orderId = UUID.randomUUID().toString();
        
        Order order = Order.builder()
            .id(orderId)
            .price(input.getPrice())
            .netAmount(input.getNetAmount())
            .category(input.getCategory())
            .item(input.getItem())
            .quantity(input.getQuantity())
            .status("PENDING")
            .userId(input.getUserId())
            .createdAt(Instant.now().toString())
            .build();
        
        orders.put(orderId, order);
        return order;
    }
    
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }
    
    public Order getOrder(String id) {
        return orders.get(id);
    }
    
    public List<Order> getOrdersByUser(String userId) {
        return orders.values().stream()
            .filter(order -> order.getUserId().equals(userId))
            .toList();
    }
    
    public static class CreateOrderInput {
        private BigDecimal price;
        private BigDecimal netAmount;
        private String category;
        private String item;
        private Integer quantity;
        private String userId;
        
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        public BigDecimal getNetAmount() { return netAmount; }
        public void setNetAmount(BigDecimal netAmount) { this.netAmount = netAmount; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getItem() { return item; }
        public void setItem(String item) { this.item = item; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }
}