package com.pooranjoyb.order_stream.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderRequestDto {
    @Positive
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Item name is mandatory")
    private String item;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity cannot be less than 1")
    private Integer quantity;
}
