package com.pooranjoyb.order.service.order.dto;

import com.pooranjoyb.order.service.order.common.enums.OrderStatus;
import com.pooranjoyb.order.service.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private BigDecimal price;
    private String category;
    private Integer quantity;
    private String item;
    private BigDecimal netAmount;
    private OrderStatus status;

    public static OrderResponseDto fromEntity(Order order) {
        return OrderResponseDto.builder()
                .item(order.getItem())
                .category(order.getCategory())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .netAmount(order.getNetAmount())
                .build();
    }
}
