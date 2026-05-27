package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.client.ProductServiceClient;
import com.pooranjoyb.graphql.gateway.dto.ProductDto;
import com.pooranjoyb.graphql.gateway.dto.ProductRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductResolverTests {

    private final ProductServiceClient productServiceClient = mock(ProductServiceClient.class);
    private final ProductResolver productResolver = new ProductResolver(productServiceClient);

    @Test
    void getProductsDelegatesToProductServiceClient() {
        ProductDto product = ProductDto.builder()
                .name("monitor")
                .inStock(true)
                .brand("Acme")
                .build();
        when(productServiceClient.getProducts()).thenReturn(List.of(product));

        List<ProductDto> products = productResolver.getProducts();

        assertThat(products).containsExactly(product);
        verify(productServiceClient).getProducts();
    }

    @Test
    void createProductDelegatesToProductServiceClient() {
        ProductRequestDto request = ProductRequestDto.builder()
                .name("monitor")
                .stock(10L)
                .image("monitor.png")
                .description("27 inch display")
                .brand("Acme")
                .rating(5L)
                .sku(12345L)
                .build();
        ProductDto createdProduct = ProductDto.builder()
                .name("monitor")
                .inStock(true)
                .brand("Acme")
                .build();
        when(productServiceClient.createProduct(request)).thenReturn(createdProduct);

        assertThat(productResolver.createProduct(request)).isEqualTo(createdProduct);
        verify(productServiceClient).createProduct(request);
    }
}
