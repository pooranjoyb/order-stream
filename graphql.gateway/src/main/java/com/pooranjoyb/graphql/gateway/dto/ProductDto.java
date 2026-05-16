package com.pooranjoyb.graphql.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Boolean inStock;
    private String image;
    private String description;
    private String brand;
    private Long rating;
}
