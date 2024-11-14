package org.example.pcbuilderproject.controller;

import org.example.pcbuilderproject.domainComponents.StorageDTO;
import org.example.pcbuilderproject.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
@CrossOrigin(origins = "http://tvoj-frontend-host.com") // zamijeni s URL-om frontenda
public class StorageController {
    private final StorageServiceImpl storageService;

    @Autowired
    public StorageController(StorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    @GetMapping("")
    public ResponseEntity<List<StorageDTO>> getAllStorages() {
        List<StorageDTO> storages = storageService.getAllStorages();
        return ResponseEntity.ok(storages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageDTO> getStorageById(@PathVariable Long id) {
        StorageDTO storage = storageService.getStorageById(id);
        return ResponseEntity.ok(storage);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StorageDTO> createStorage(@RequestBody StorageDTO storage) {
        StorageDTO savedStorage = storageService.createStorage(storage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStorage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStorage(@PathVariable Long id) {
        storageService.deleteStorage(id);
        return ResponseEntity.noContent().build();
    }
}