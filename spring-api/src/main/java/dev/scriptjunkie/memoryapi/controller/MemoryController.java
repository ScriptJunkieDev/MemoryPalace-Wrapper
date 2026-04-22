package dev.scriptjunkie.memoryapi.controller;

import dev.scriptjunkie.memoryapi.dto.MemoryAddRequest;
import dev.scriptjunkie.memoryapi.dto.MemorySearchRequest;
import dev.scriptjunkie.memoryapi.service.MemoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/memory")
public class MemoryController {

    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("/status")
    public ResponseEntity<Map> status() {
        return ResponseEntity.ok(memoryService.status());
    }

    @PostMapping("/reconnect")
    public ResponseEntity<Map> reconnect() {
        return ResponseEntity.ok(memoryService.reconnect());
    }

    @PostMapping("/search")
    public ResponseEntity<Map> search(@RequestBody MemorySearchRequest request) {
        return ResponseEntity.ok(memoryService.search(request));
    }

    @PostMapping("/add")
    public ResponseEntity<Map> add(@RequestBody MemoryAddRequest request) {
        return ResponseEntity.ok(memoryService.add(request));
    }
}
