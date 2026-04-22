package dev.scriptjunkie.memoryapi.dto;

public record MemorySearchRequest(String query, Integer limit, String tenantId, String userId) {}
