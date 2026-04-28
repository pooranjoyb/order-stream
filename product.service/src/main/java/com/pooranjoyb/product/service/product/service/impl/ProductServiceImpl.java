package com.pooranjoyb.product.service.product.service.impl;

import com.pooranjoyb.product.service.product.dto.ProductRequestDto;
import com.pooranjoyb.product.service.product.dto.ProductResponseDto;
import com.pooranjoyb.product.service.product.entity.Product;
import com.pooranjoyb.product.service.product.repository.ProductRepository;
import com.pooranjoyb.product.service.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = Product.builder()
                .name(productRequestDto.getName())
                .brand(productRequestDto.getBrand())
                .stock(productRequestDto.getStock())
                .sku(productRequestDto.getSku())
                .image(productRequestDto.getImage())
                .description(productRequestDto.getDescription())
                .rating(productRequestDto.getRating()).build();
        return ProductResponseDto.fromEntity(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>(List.of());
        for(Product p : productList) {
            productResponseDtoList.add(ProductResponseDto.fromEntity(p));
        }

        return productResponseDtoList;
    }
}
