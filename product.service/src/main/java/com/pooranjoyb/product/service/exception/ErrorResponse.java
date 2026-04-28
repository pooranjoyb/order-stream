package com.pooranjoyb.product.service.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message, String detail) {
}