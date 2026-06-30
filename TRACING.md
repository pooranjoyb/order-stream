# Distributed Tracing with Zipkin

This document explains the distributed tracing implementation across the order-stream microservices architecture.

## Overview

The order-stream microservices system now uses **Zipkin** and **Micrometer Tracing** (with Brave instrumentation) to enable end-to-end request tracing across services. This allows developers to:

- Track request flow across Order Service → Product Service → GraphQL Gateway
- Identify performance bottlenecks and latencies in multi-service calls
- Debug failures by correlating logs across service boundaries
- Analyze service dependencies and communication patterns

## Architecture

### Components

1. **Micrometer Tracing Bridge (Brave)**
   - Auto-instruments HTTP requests and RabbitMQ messages
   - Propagates trace IDs across service boundaries
   - Assigns unique trace IDs and span IDs to all operations

2. **Zipkin**
   - Centralized tracing backend
   - Collects and visualizes traces from all microservices
   - Provides trace search and analysis UI

3. **Trace Propagation**
   - All HTTP requests include `traceparent` and `tracestate` headers
   - RabbitMQ messages include trace context in message headers
   - Logs automatically include trace ID and span ID

## Setup & Configuration

### Prerequisites

- Docker and Docker Compose
- Java 21+
- Spring Boot 4.0.6+

### Starting Services

```bash
# Start all services including Zipkin
docker-compose up -d

# Verify services are running
docker-compose ps
```

### Service Ports

| Service | Port | URL |
|---------|------|-----|
| GraphQL Gateway | 5000 | http://localhost:5000/graphql |
| Order Service | 8080 | http://localhost:8080 |
| Product Service | 8081 | http://localhost:8081 |
| Zipkin UI | 9411 | http://localhost:9411 |
| PostgreSQL | 5435 | localhost:5435 |
| RabbitMQ | 5672 | localhost:5672 |
| RabbitMQ UI | 15672 | http://localhost:15672 |

### Configuration

Tracing is automatically configured in each service's `application.properties`:

```properties
# Distributed Tracing - Zipkin/Brave
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
management.endpoints.web.exposure.include=health,info,metrics,prometheus
```

**Configuration breakdown:**

- `sampling.probability=1.0`: Sample 100% of requests (captures all traces)
  - In production, reduce to 0.1 or lower to reduce overhead
- `zipkin.tracing.endpoint`: Zipkin backend URL to send traces
- `endpoints.web.exposure.include`: Enable actuator endpoints for metrics

## Using Distributed Tracing

### Viewing Traces in Zipkin

1. Open Zipkin UI: http://localhost:9411
2. Click "Find Traces"
3. Select service: `graphql.gateway`, `order.service`, or `product.service`
4. Click "Find Traces" to see recent traces
5. Click on a trace to view detailed span information

### Sample Trace Flow

**Example: Create Order Request**

```
Request: POST /graphql (Gateway)
├─ Span: graphql.gateway - HTTP request processing
│  ├─ Span: order.service/createOrder - REST call to order service
│  │  ├─ Span: PostgreSQL - Save order to database
│  │  └─ Span: RabbitMQ - Publish order event
│  └─ Span: product.service/getProduct - REST call to product service
│     └─ Span: PostgreSQL - Fetch product details
└─ Response: GraphQL JSON
```

Each span includes:
- **Trace ID**: Same across all services for single request
- **Span ID**: Unique for each operation
- **Duration**: How long the operation took
- **Status**: Success or failure
- **Tags**: Service name, HTTP method, endpoint, etc.

### Analyzing Traces

#### Performance Bottleneck Example

If a request takes 5 seconds:
1. Open the trace in Zipkin
2. Examine span durations:
   - Gateway processing: 100ms
   - Order Service HTTP call: 4800ms ⚠️ (bottleneck)
   - Product Service HTTP call: 50ms
3. Root cause: Order Service database query is slow

#### Debug Multi-Service Failure

If an order creation fails across services:
1. Get the Trace ID from error logs: `trace-id=a1b2c3d4e5f6g7h8`
2. Search Zipkin for this Trace ID
3. View the trace to see which service failed
4. Check that service's logs with the same trace ID:
   ```bash
   # Order Service logs
   docker logs order-service | grep "trace-id=a1b2c3d4e5f6g7h8"
   ```

## Integration with Logging

All application logs automatically include trace context:

```
2026-06-15 10:30:45.123  INFO trace-id=a1b2c3d4e5f6g7h8 span-id=xyz789 ... OrderService: Creating order
2026-06-15 10:30:45.456  INFO trace-id=a1b2c3d4e5f6g7h8 span-id=abc123 ... ProductService: Fetching product
```

Use trace ID to correlate logs across services:
```bash
# Find all logs for a specific trace
docker logs order-service | grep "trace-id=a1b2c3d4e5f6g7h8"
docker logs product-service | grep "trace-id=a1b2c3d4e5f6g7h8"
docker logs graphql-gateway | grep "trace-id=a1b2c3d4e5f6g7h8"
```

## Production Configuration

### Sampling Strategy

For high-traffic services, sampling all requests (100%) causes overhead. Use probabilistic sampling:

```properties
# Sample 10% of requests
management.tracing.sampling.probability=0.1

# For critical services, use 20%
management.tracing.sampling.probability=0.2
```

### Alternative Tracing Backends

Replace Zipkin with Jaeger (OpenTelemetry compatible):

```properties
management.otlp.metrics.export.url=http://jaeger:4317
management.tracing.exporter.otlp.endpoint=http://jaeger:4317
```

### Disable Tracing

To disable tracing in development:

```properties
management.tracing.enabled=false
```

## Troubleshooting

### Traces Not Appearing in Zipkin

1. **Verify Zipkin is running:**
   ```bash
   curl http://localhost:9411/api/v2/services
   ```

2. **Check service logs for errors:**
   ```bash
   docker logs order-service | grep -i "zipkin\|tracing"
   ```

3. **Verify network connectivity:**
   ```bash
   docker exec order-service ping zipkin
   ```

4. **Check sampling probability:**
   - If set to 0.0, no traces will be sent
   - Increase to 0.1 or 1.0 for testing

### High Latency from Tracing

Tracing overhead is typically <5%. If higher:
1. Reduce sampling probability
2. Disable tracing in heavy-load services
3. Use asynchronous span reporting

### Trace Data Lost

Increase Zipkin storage:
```yaml
# docker-compose.yml
zipkin:
  environment:
    STORAGE_TYPE: mem
    # Or use persistent storage
    STORAGE_TYPE: mysql
```

## References

- [Micrometer Tracing Documentation](https://micrometer.io/docs/tracing)
- [Zipkin Official Documentation](https://zipkin.io/)
- [Spring Boot Tracing Guide](https://spring.io/blog/2022/12/22/observability-with-spring-boot-3-0)
- [OpenTelemetry Spec](https://opentelemetry.io/docs/reference/specification/)
