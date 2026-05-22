package com.pooranjoyb.graphql.gateway.client;

import com.pooranjoyb.graphql.gateway.core.Constants;
import com.pooranjoyb.graphql.gateway.dto.ProductDto;
import com.pooranjoyb.graphql.gateway.dto.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    private final BaseWebClient baseWebClient;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public List<ProductDto> getProducts() {
        return baseWebClient.getList(productServiceUrl, Constants.PRODUCT_GET_PRODUCTS, ProductDto.class);
    }

    public ProductDto createProduct(ProductRequestDto productRequestDto) {
        return baseWebClient.post(
                productServiceUrl,
                Constants.PRODUCT_CREATE_PRODUCT,
                productRequestDto,
                ProductDto.class);
    }
}
