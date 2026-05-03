package com.pooranjoyb.product.service.mapper;

import com.pooranjoyb.product.service.product.entity.Product;
import com.pooranjoyb.shared.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product entity) {
        if (entity == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(null);
        dto.setStock(entity.getStock());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStock(dto.getStock());
        return entity;
    }
}