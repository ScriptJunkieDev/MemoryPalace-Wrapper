package dev.scriptjunkie.memoryapi.controller;

import dev.scriptjunkie.memoryapi.dto.MemoryAddRequest;
import dev.scriptjunkie.memoryapi.dto.MemorySearchRequest;
import dev.scriptjunkie.memoryapi.service.MemoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memory")
public class MemoryController {
    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(memoryService.status());
    }

    @PostMapping("/reconnect")
    public ResponseEntity<String> reconnect() {
        return ResponseEntity.ok(memoryService.reconnect());
    }

    @PostMapping("/search")
    public ResponseEntity<String> search(@RequestBody MemorySearchRequest request) {
        return ResponseEntity.ok(memoryService.search(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody MemoryAddRequest request) {
        return ResponseEntity.ok(memoryService.add(request));
    }
}
