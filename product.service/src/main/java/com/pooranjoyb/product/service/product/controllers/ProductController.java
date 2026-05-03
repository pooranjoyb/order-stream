package com.pooranjoyb.product.service.product.controllers;

import com.pooranjoyb.product.service.product.dto.ProductRequestDto;
import com.pooranjoyb.product.service.product.dto.ProductResponseDto;
import com.pooranjoyb.product.service.product.entity.Product;
import com.pooranjoyb.product.service.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> responses = products.stream()
                .map(this::convertToProductResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = convertToProduct(productRequestDto);
        Product saved = productService.createProduct(product);
        ProductResponseDto response = convertToProductResponseDto(saved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Product convertToProduct(ProductRequestDto request) {
        if (request == null) return null;
        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setImage(request.getImage());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setSku(request.getSku());
        product.setRating(request.getRating());
        return product;
    }

    private ProductResponseDto convertToProductResponseDto(Product product) {
        if (product == null) return null;
        return ProductResponseDto.builder()
                .name(product.getName())
                .brand(product.getBrand())
                .description(product.getDescription())
                .image(product.getImage())
                .inStock(product.getStock() > 0)
                .rating(product.getRating())
                .build();
    }
}
