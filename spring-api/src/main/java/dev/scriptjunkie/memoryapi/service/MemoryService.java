package dev.scriptjunkie.memoryapi.service;

import dev.scriptjunkie.memoryapi.dto.MemoryAddRequest;
import dev.scriptjunkie.memoryapi.dto.MemorySearchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MemoryService {
    private final WebClient memoryWebClient;

    public MemoryService(WebClient memoryWebClient) {
        this.memoryWebClient = memoryWebClient;
    }

    public String status() {
        return memoryWebClient.get().uri("/status").retrieve().bodyToMono(String.class).block();
    }

    public String reconnect() {
        return memoryWebClient.post().uri("/reconnect").retrieve().bodyToMono(String.class).block();
    }

    public String search(MemorySearchRequest request) {
        return memoryWebClient.post().uri("/search").bodyValue(request).retrieve().bodyToMono(String.class).block();
    }

    public String add(MemoryAddRequest request) {
        return memoryWebClient.post().uri("/add").bodyValue(request).retrieve().bodyToMono(String.class).block();
    }
}
