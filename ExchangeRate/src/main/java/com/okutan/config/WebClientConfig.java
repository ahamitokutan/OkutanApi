package com.okutan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${external.api.base-url}")
    private String baseUrl;

    @Value("${external.api.api-key}")
    private String apiKey;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        System.out.println(baseUrl);
        System.out.println(apiKey);
        return webClientBuilder.defaultHeaders(a -> a.add("apikey", apiKey)).baseUrl(baseUrl).build();
    }
}