package com.pooranjoyb.shared.contracts;

import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.shared.common.ProductEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReservationResultEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long productId;
    private String item;
    private OrderStatus status;
    private ProductEventType productEventType;
    private String reason;
}
