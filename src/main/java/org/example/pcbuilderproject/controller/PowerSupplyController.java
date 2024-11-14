package org.example.pcbuilderproject.controller;

import org.example.pcbuilderproject.domainComponents.PowerSupplyDTO;
import org.example.pcbuilderproject.service.PowerSupplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/power-supply")
@CrossOrigin(origins = "http://tvoj-frontend-host.com") // zamijeni s URL-om frontenda
public class PowerSupplyController {
    private final PowerSupplyServiceImpl powerSupplyService;

    @Autowired
    public PowerSupplyController(PowerSupplyServiceImpl powerSupplyService) {
        this.powerSupplyService = powerSupplyService;
    }

    @GetMapping("")
    public ResponseEntity<List<PowerSupplyDTO>> getAllPowerSupplies() {
        List<PowerSupplyDTO> powerSupplies = powerSupplyService.getAllPowerSupplies();
        return ResponseEntity.ok(powerSupplies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerSupplyDTO> getPowerSupplyById(@PathVariable Long id) {
        PowerSupplyDTO powerSupply = powerSupplyService.getPowerSupplyById(id);
        return ResponseEntity.ok(powerSupply);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PowerSupplyDTO> createPowerSupply(@RequestBody PowerSupplyDTO powerSupply) {
        PowerSupplyDTO savedPowerSupply = powerSupplyService.createPowerSupply(powerSupply);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPowerSupply);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePowerSupply(@PathVariable Long id) {
        powerSupplyService.deletePowerSupply(id);
        return ResponseEntity.noContent().build();
    }
}
