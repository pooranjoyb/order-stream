package com.pooranjoyb.product.service.product.service;

import com.pooranjoyb.product.service.product.dto.ProductRequestDto;
import com.pooranjoyb.product.service.product.dto.ProductResponseDto;
import com.pooranjoyb.shared.contracts.OrderCreatedEvent;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto product);
    List<ProductResponseDto> getAllProducts();
    void consumeOrder(OrderCreatedEvent orderCreatedEvent);
}
