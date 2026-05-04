package com.pooranjoyb.graphql.gateway.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private BigDecimal price;
    private BigDecimal netAmount;
    private String category;
    private String item;
    private Integer quantity;
    private String status;
    private String userId;
    private String createdAt;
}