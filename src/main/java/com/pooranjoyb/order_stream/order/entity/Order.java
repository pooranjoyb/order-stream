package com.pooranjoyb.order_stream.order.entity;

import com.pooranjoyb.order_stream.core.entity.AuditFields;
import com.pooranjoyb.order_stream.order.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
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
