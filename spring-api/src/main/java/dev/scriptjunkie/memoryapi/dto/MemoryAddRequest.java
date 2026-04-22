package dev.scriptjunkie.memoryapi.dto;

import java.util.List;

public record MemoryAddRequest(
        String text,
        String source,
        List<String> tags,
        String tenantId,
        String userId
) {}
