package org.example.pcbuilderproject.domain;


import org.example.pcbuilderproject.repository.PCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pc")
@CrossOrigin(origins = "http://localhost:3000")
public class PCController {


    @Autowired
    private PCRepository pcRepository;

    // Get all PCs
    @GetMapping
    public ResponseEntity<List<PC>> getAllPCs() {
        List<PC> pcs = pcRepository.findAll();
        return ResponseEntity.ok(pcs);
    }

    // Get PC by ID
    @GetMapping("/{id}")
    public ResponseEntity<PC> getPCById(@PathVariable Long id) {
        PC pc = pcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));
        return ResponseEntity.ok(pc);
    }

    // Create new PC
    @PostMapping
    public ResponseEntity<PC> createPC(@RequestBody PC newPC) {
        PC savedPC = pcRepository.save(newPC);
        return ResponseEntity.ok(savedPC);
    }

    // Update PC
    @PutMapping("/{id}")
    public ResponseEntity<PC> updatePC(@PathVariable Long id, @RequestBody PC updatedPC) {
        PC pc = pcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));

        pc.setName(updatedPC.getName());
        pc.setProcessor(updatedPC.getProcessor());
        pc.setMotherboard(updatedPC.getMotherboard());
        pc.setGraphicsCard(updatedPC.getGraphicsCard());
        pc.setMemory(updatedPC.getMemory());
        pc.setStorage(updatedPC.getStorage());
        pc.setCaseEntity(updatedPC.getCaseEntity());
        pc.setPowerSupply(updatedPC.getPowerSupply());

        pcRepository.save(pc);
        return ResponseEntity.ok(pc);
    }



    // Delete PC
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePC(@PathVariable Long id) {
        pcRepository.deleteById(id);
        return ResponseEntity.ok("PC deleted successfully");
    }
}
