package dev.scriptjunkie.memoryapi.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(MemoryAdapterProperties.class)
public class WebClientConfig {

    @Bean
    public WebClient memoryWebClient(WebClient.Builder builder, MemoryAdapterProperties properties) {
        return builder.baseUrl(properties.getBaseUrl()).build();
    }
}
