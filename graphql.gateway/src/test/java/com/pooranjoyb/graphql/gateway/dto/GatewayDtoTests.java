package com.pooranjoyb.graphql.gateway.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class GatewayDtoTests {

    @Test
    void orderRequestDtoCarriesOrderServicePayloadFields() {
        OrderRequestDto request = OrderRequestDto.builder()
                .price(BigDecimal.valueOf(199.99))
                .category("electronics")
                .item("keyboard")
                .quantity(2)
                .build();

        assertThat(request.getPrice()).isEqualByComparingTo("199.99");
        assertThat(request.getCategory()).isEqualTo("electronics");
        assertThat(request.getItem()).isEqualTo("keyboard");
        assertThat(request.getQuantity()).isEqualTo(2);
    }

    @Test
    void orderDtoCarriesOrderServiceResponseFields() {
        OrderDto response = OrderDto.builder()
                .id(42L)
                .price(BigDecimal.valueOf(100))
                .category("books")
                .item("architecture guide")
                .quantity(1)
                .netAmount(BigDecimal.valueOf(100))
                .status("CREATED")
                .build();

        assertThat(response.getId()).isEqualTo(42L);
        assertThat(response.getNetAmount()).isEqualByComparingTo("100");
        assertThat(response.getStatus()).isEqualTo("CREATED");
    }

    @Test
    void productRequestDtoCarriesProductServicePayloadFields() {
        ProductRequestDto request = ProductRequestDto.builder()
                .name("monitor")
                .stock(10L)
                .image("monitor.png")
                .description("27 inch display")
                .brand("Acme")
                .rating(5L)
                .sku(12345L)
                .build();

        assertThat(request.getName()).isEqualTo("monitor");
        assertThat(request.getStock()).isEqualTo(10L);
        assertThat(request.getSku()).isEqualTo(12345L);
    }

    @Test
    void productDtoCarriesProductServiceResponseFields() {
        ProductDto response = ProductDto.builder()
                .name("monitor")
                .inStock(true)
                .image("monitor.png")
                .description("27 inch display")
                .brand("Acme")
                .rating(5L)
                .build();

        assertThat(response.getName()).isEqualTo("monitor");
        assertThat(response.getInStock()).isTrue();
        assertThat(response.getBrand()).isEqualTo("Acme");
    }
}
