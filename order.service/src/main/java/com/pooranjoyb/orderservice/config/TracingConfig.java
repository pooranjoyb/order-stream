package com.pooranjoyb.orderservice.config;

import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Configuration;

/**
 * Distributed tracing configuration for microservice observability.
 * Enables automatic trace propagation across service boundaries using Zipkin/Brave.
 * All HTTP requests and message queues are automatically instrumented with trace IDs.
 */
@Configuration
public class TracingConfig {
    // Micrometer tracing is auto-configured by Spring Boot
    // Trace IDs are automatically added to all logs and requests
}
