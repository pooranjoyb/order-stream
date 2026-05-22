package com.pooranjoyb.graphql.gateway.resolver;

import com.pooranjoyb.graphql.gateway.client.ProductServiceClient;
import com.pooranjoyb.graphql.gateway.dto.ProductDto;
import com.pooranjoyb.graphql.gateway.dto.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {

    private final ProductServiceClient productServiceClient;

    @QueryMapping
    public List<ProductDto> getProducts() {
        return productServiceClient.getProducts();
    }

    @MutationMapping
    public ProductDto createProduct(@Argument("product") ProductRequestDto productRequestDto) {
        return productServiceClient.createProduct(productRequestDto);
    }
}
