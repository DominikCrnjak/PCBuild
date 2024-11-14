package org.example.pcbuilderproject.controller;

import org.example.pcbuilderproject.domainComponents.ProcessorDTO;
import org.example.pcbuilderproject.service.ProcessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProcessorController {
    private final ProcessorServiceImpl processorService;

    @Autowired
    public ProcessorController(ProcessorServiceImpl processorService) {
        this.processorService = processorService;
    }


    // Endpoint za dohvaćanje svih procesora
    @GetMapping("")
    public ResponseEntity<List<ProcessorDTO>> getAllProcessors() {
        List<ProcessorDTO> processors = processorService.getAllProcessors();
        return ResponseEntity.ok(processors);
    }

    // Endpoint za dohvaćanje procesora po id
    @GetMapping("/{id}")
    public ResponseEntity<ProcessorDTO> getProcessorById(@PathVariable Long id) {
        ProcessorDTO processor = processorService.getProcessorById(id);
        return ResponseEntity.ok(processor);
    }

    // Endpoint za spremanje kreiranog procesora
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProcessorDTO> createProcessor(@RequestBody ProcessorDTO processor) {
        ProcessorDTO savedProcessor = processorService.createProcessor(processor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcessor);
    }

    // Endpoint za brisanje procesora
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProcessor(@PathVariable Long id) {
        processorService.deleteProcessor(id);
        return ResponseEntity.noContent().build();
    }
}
