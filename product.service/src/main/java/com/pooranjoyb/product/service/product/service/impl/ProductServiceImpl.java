package com.pooranjoyb.product.service.product.service.impl;

import com.pooranjoyb.product.service.messaging.ProductReservationEventProducer;
import com.pooranjoyb.product.service.product.entity.Product;
import com.pooranjoyb.product.service.product.repository.ProductRepository;
import com.pooranjoyb.product.service.product.service.ProductService;
import com.pooranjoyb.shared.common.OrderStatus;
import com.pooranjoyb.shared.common.ProductEventType;
import com.pooranjoyb.shared.contracts.OrderCreatedEvent;
import com.pooranjoyb.shared.contracts.ProductReservationResultEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductReservationEventProducer productReservationEventProducer;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public java.util.List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void consumeOrder(OrderCreatedEvent orderCreatedEvent) {
        String orderItemName = orderCreatedEvent.getItem();

        Product product = productRepository.findByName(orderItemName);
        if (ObjectUtils.isNotEmpty(product) && product.getStock() >= 0) {
            ProductReservationResultEvent productReservationResultEvent = ProductReservationResultEvent.builder()
                    .productEventType(product.getStock() > 0 ? ProductEventType.PRODUCT_RESERVED : ProductEventType.PRODUCT_REJECTED)
                    .productId(product.getId())
                    .orderId(orderCreatedEvent.getOrderId())
                    .item(orderItemName)
                    .reason(product.getStock() == 0 || product.getStock() < orderCreatedEvent.getQuantity() ? "Out of Stock" : null)
                    .status(OrderStatus.BOOKED).build();

            product.setStock(product.getStock() - orderCreatedEvent.getQuantity());
            productRepository.save(product);
            productReservationEventProducer.publishProductReservationEvent(productReservationResultEvent);

        } else {
            ProductReservationResultEvent productReservationResultEvent = ProductReservationResultEvent.builder()
                    .productEventType(ProductEventType.PRODUCT_REJECTED)
                    .orderId(orderCreatedEvent.getOrderId())
                    .item(orderItemName)
                    .reason("Product Not Found")
                    .status(OrderStatus.CANCELLED).build();

            throw new RuntimeException("Product not found");
        }
    }
}
