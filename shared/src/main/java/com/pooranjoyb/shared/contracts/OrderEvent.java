package com.pooranjoyb.shared.contracts;


import com.pooranjoyb.shared.common.OrderEventType;
import com.pooranjoyb.shared.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String item;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal netAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private OrderEventType eventType;
}
