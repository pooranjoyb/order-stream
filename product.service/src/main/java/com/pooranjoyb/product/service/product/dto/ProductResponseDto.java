package com.pooranjoyb.product.service.product.dto;

import com.pooranjoyb.product.service.product.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private String name;
    private Boolean inStock;
    private String image;
    private String description;
    private String brand;
    private Long rating;

    public static ProductResponseDto fromEntity(Product product) {
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
