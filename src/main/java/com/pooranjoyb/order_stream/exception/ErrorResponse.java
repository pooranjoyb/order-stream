package com.pooranjoyb.order_stream.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message, String detail) {
}