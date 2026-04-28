package com.pooranjoyb.product.service.product.entity;

import com.pooranjoyb.product.service.entity.AuditFields;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="products")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product extends AuditFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long stock;
    private String image;
    private String description;
    private String brand;
    private Long sku;
    private Long rating;
}
