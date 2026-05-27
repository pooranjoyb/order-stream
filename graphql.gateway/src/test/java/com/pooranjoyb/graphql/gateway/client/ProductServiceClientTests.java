package com.pooranjoyb.graphql.gateway.client;

import com.pooranjoyb.graphql.gateway.core.Constants;
import com.pooranjoyb.graphql.gateway.dto.ProductDto;
import com.pooranjoyb.graphql.gateway.dto.ProductRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceClientTests {

    private final BaseWebClient baseWebClient = mock(BaseWebClient.class);
    private final ProductServiceClient productServiceClient = new ProductServiceClient(baseWebClient);

    ProductServiceClientTests() {
        ReflectionTestUtils.setField(productServiceClient, "productServiceUrl", "http://product-service");
    }

    @Test
    void getProductsCallsProductServiceListEndpoint() {
        ProductDto product = ProductDto.builder().name("monitor").inStock(true).build();
        when(baseWebClient.getList(
                "http://product-service",
                Constants.PRODUCT_GET_PRODUCTS,
                ProductDto.class)).thenReturn(List.of(product));

        List<ProductDto> products = productServiceClient.getProducts();

        assertThat(products).containsExactly(product);
        verify(baseWebClient).getList(
                "http://product-service",
                Constants.PRODUCT_GET_PRODUCTS,
                ProductDto.class);
    }

    @Test
    void createProductPostsProductPayloadToProductService() {
        ProductRequestDto request = ProductRequestDto.builder()
                .name("monitor")
                .stock(10L)
                .sku(12345L)
                .build();
        ProductDto createdProduct = ProductDto.builder().name("monitor").inStock(true).build();
        when(baseWebClient.post(
                "http://product-service",
                Constants.PRODUCT_CREATE_PRODUCT,
                request,
                ProductDto.class)).thenReturn(createdProduct);

        ProductDto response = productServiceClient.createProduct(request);

        assertThat(response).isEqualTo(createdProduct);
        verify(baseWebClient).post(
                "http://product-service",
                Constants.PRODUCT_CREATE_PRODUCT,
                request,
                ProductDto.class);
    }
}
