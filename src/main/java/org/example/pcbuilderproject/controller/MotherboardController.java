package org.example.pcbuilderproject.controller;

import org.example.pcbuilderproject.domainComponents.MotherboardDTO;
import org.example.pcbuilderproject.service.MotherboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motherboard")
@CrossOrigin(origins = "http://tvoj-frontend-host.com") // zamijeni s URL-om frontenda
public class MotherboardController {
    private final MotherboardServiceImpl motherboardService;

    @Autowired
    public MotherboardController(MotherboardServiceImpl motherboardService) {
        this.motherboardService = motherboardService;
    }

    @GetMapping("")
    public ResponseEntity<List<MotherboardDTO>> getAllMotherboards() {
        List<MotherboardDTO> motherboards = motherboardService.getAllMotherboards();
        return ResponseEntity.ok(motherboards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotherboardDTO> getMotherboardById(@PathVariable Long id) {
        MotherboardDTO motherboard = motherboardService.getMotherboardById(id);
        return ResponseEntity.ok(motherboard);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MotherboardDTO> createMotherboard(@RequestBody MotherboardDTO motherboard) {
        MotherboardDTO savedMotherboard = motherboardService.createMotherboard(motherboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMotherboard);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable Long id) {
        motherboardService.deleteMotherboard(id);
        return ResponseEntity.noContent().build();
    }
}
