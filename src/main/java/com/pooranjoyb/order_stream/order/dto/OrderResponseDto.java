package com.pooranjoyb.order_stream.order.dto;

import com.pooranjoyb.order_stream.order.common.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private BigDecimal price;
    private String category;
    private Integer quantity;
    private String item;
    private BigDecimal netAmount;
    private OrderStatus status;
}
