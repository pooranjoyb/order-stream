package com.pooranjoyb.order.service.order.entity;

import com.pooranjoyb.order.service.core.entity.AuditFields;
import com.pooranjoyb.order.service.order.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order extends AuditFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private BigDecimal netAmount;
    private String category;
    private String item;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
