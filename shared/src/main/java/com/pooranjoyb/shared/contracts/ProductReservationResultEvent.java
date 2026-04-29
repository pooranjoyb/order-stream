package com.pooranjoyb.shared.contracts;

import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.shared.common.ProductEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservationResultEvent {
    private Long orderId;
    private Long productId;
    private String item;
    private OrderStatus status;
    private ProductEventType productEventType;
    private String reason;
}
