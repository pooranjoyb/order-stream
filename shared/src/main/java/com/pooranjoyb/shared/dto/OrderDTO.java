package com.pooranjoyb.shared.dto;

import com.pooranjoyb.shared.common.OrderStatus;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long productId;
    private Long quantity;
    private Double price;
    private String category;
    private String item;
    private Double netAmount;
    private OrderStatus status;
}