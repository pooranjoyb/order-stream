package com.pooranjoyb.product.service.product.service;

import com.pooranjoyb.product.service.product.dto.ProductRequestDto;
import com.pooranjoyb.product.service.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto product);
    List<ProductResponseDto> getAllProducts();
}
