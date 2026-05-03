package com.pooranjoyb.product.service.product.service;

import com.pooranjoyb.product.service.product.entity.Product;
import com.pooranjoyb.shared.contracts.OrderCreatedEvent;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    void consumeOrder(OrderCreatedEvent orderCreatedEvent);
}
