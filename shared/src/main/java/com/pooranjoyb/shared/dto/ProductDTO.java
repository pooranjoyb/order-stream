package com.pooranjoyb.shared.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Long stock;
    private String image;
    private String description;
    private String brand;
    private Long sku;
    private Long rating;
    private Boolean inStock;
}