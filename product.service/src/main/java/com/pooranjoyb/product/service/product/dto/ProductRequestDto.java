package com.pooranjoyb.product.service.product.dto;

import com.pooranjoyb.product.service.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    @NotBlank(message = "Product Name should not be blank")
    private String name;

    @Positive(message = "Stock should be positive")
    @NotNull(message = "Stock shouldn't be empty")
    private Long stock;
    private String image;
    private String description;
    private String brand;
    private Long rating;

    @Positive(message = "Unit should be positive")
    @NotNull(message = "Unit cannot be empty")
    private Long sku;
}
