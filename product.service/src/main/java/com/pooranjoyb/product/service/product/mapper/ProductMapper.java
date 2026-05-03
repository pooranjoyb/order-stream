package com.pooranjoyb.product.service.product.mapper;

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
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setSku(entity.getSku());
        dto.setRating(entity.getRating());
        dto.setInStock(entity.getStock() > 0);
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStock(dto.getStock());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setBrand(dto.getBrand());
        entity.setSku(dto.getSku());
        entity.setRating(dto.getRating());
        return entity;
    }
}