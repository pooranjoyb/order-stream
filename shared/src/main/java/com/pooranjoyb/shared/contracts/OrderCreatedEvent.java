package com.pooranjoyb.shared.contracts;


import com.pooranjoyb.shared.common.OrderEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String item;
    private Integer quantity;
    private LocalDateTime createdAt;
    private OrderEventType eventType;
}
