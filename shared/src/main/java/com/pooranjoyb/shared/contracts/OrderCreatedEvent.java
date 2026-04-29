package com.pooranjoyb.shared.contracts;

import com.pooranjoyb.shared.common.OrderEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private String item;
    private Integer quantity;
    private LocalDateTime createdAt;
    private OrderEventType eventType;
}
