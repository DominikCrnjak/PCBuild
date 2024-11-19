package org.example.pcbuilderproject.componentsController;

import org.example.pcbuilderproject.componentsDomain.MemoryDTO;
import org.example.pcbuilderproject.componentsService.MemoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memory")
@CrossOrigin(origins = "http://localhost:3000")
public class MemoryController {
    private final MemoryServiceImpl memoryService;

    @Autowired
    public MemoryController(MemoryServiceImpl memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<MemoryDTO>> getAllMemories() {
        List<MemoryDTO> memories = memoryService.getAllMemories();
        return ResponseEntity.ok(memories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoryDTO> getMemoryById(@PathVariable Long id) {
        MemoryDTO memory = memoryService.getMemoryById(id);
        return ResponseEntity.ok(memory);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemoryDTO> createMemory(@RequestBody MemoryDTO memory) {
        MemoryDTO savedMemory = memoryService.createMemory(memory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMemory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }
}