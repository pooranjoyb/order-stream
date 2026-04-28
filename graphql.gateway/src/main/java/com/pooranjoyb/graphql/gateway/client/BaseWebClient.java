package com.pooranjoyb.graphql.gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseWebClient {

    private final WebClient.Builder webClientBuilder;

    public <T> List<T> getList(String baseUrl, String uri, Class<T> responseType) {

        return webClientBuilder
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(responseType)
                .collectList()
                .block();
    }

    public <T> T getObject(String baseUrl, String uri, Class<T> responseType) {

        return webClientBuilder
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
