package dev.scriptjunkie.memoryapi.service;

import dev.scriptjunkie.memoryapi.dto.MemoryAddRequest;
import dev.scriptjunkie.memoryapi.dto.MemorySearchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class MemoryService {

    private final WebClient memoryWebClient;

    public MemoryService(WebClient memoryWebClient) {
        this.memoryWebClient = memoryWebClient;
    }

    public Map status() {
        return memoryWebClient.get()
                .uri("/status")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map reconnect() {
        return memoryWebClient.post()
                .uri("/reconnect")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map search(MemorySearchRequest request) {
        MemorySearchRequest normalized = new MemorySearchRequest(
                request.query(),
                request.limit() == null ? 10 : request.limit(),
                request.tenantId(),
                request.userId()
        );

        return memoryWebClient.post()
                .uri("/search")
                .bodyValue(normalized)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map add(MemoryAddRequest request) {
        MemoryAddRequest normalized = new MemoryAddRequest(
                request.text(),
                request.source(),
                request.tags() == null ? java.util.List.of() : request.tags(),
                request.tenantId(),
                request.userId()
        );

        return memoryWebClient.post()
                .uri("/add")
                .bodyValue(normalized)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
