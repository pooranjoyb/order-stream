package com.pooranjoyb.graphql.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private Long stock;
    private String image;
    private String description;
    private String brand;
    private Long rating;
    private Long sku;
}
