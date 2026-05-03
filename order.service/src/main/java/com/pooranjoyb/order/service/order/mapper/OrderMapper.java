package com.pooranjoyb.order.service.order.mapper;

import com.pooranjoyb.order.service.order.entity.Order;
import com.pooranjoyb.shared.dto.OrderDTO;

import java.math.BigDecimal;

public class OrderMapper {

    public static OrderDTO toDTO(Order entity) {
        if (entity == null) return null;
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setProductId(null);
        dto.setQuantity(entity.getQuantity() != null ? entity.getQuantity().longValue() : null);
        dto.setPrice(entity.getPrice() != null ? entity.getPrice().doubleValue() : null);
        return dto;
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity() != null ? dto.getQuantity().intValue() : null);
        entity.setPrice(dto.getPrice() != null ? BigDecimal.valueOf(dto.getPrice()) : null);
        return entity;
    }
}