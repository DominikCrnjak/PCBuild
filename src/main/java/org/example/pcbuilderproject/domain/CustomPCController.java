package org.example.pcbuilderproject.domain;

import jakarta.transaction.Transactional;
import org.example.pcbuilderproject.repository.CustomPCRepository;
import org.example.pcbuilderproject.repository.OfferRepository;
import org.example.pcbuilderproject.repository.PCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custompc")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomPCController {


    @Autowired
    private CustomPCRepository customPCRepository;

    @Autowired
    private OfferRepository offerRepository;

    // Get all PCs
    @GetMapping
    public ResponseEntity<List<CustomPC>> getAllPCs() {
        List<CustomPC> pcs = customPCRepository.findAll();
        return ResponseEntity.ok(pcs);
    }

    // Get PC by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomPC> getPCById(@PathVariable Long id) {
        CustomPC pc = customPCRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));
        return ResponseEntity.ok(pc);
    }

    // Update PC
    @PutMapping("/{id}")
    public ResponseEntity<CustomPC> updatePC(@PathVariable Long id, @RequestBody CustomPC updatedPC) {
        CustomPC pc = customPCRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PC not found"));

        pc.setName(updatedPC.getName());
        pc.setProcessor(updatedPC.getProcessor());
        pc.setMotherboard(updatedPC.getMotherboard());
        pc.setGraphicsCard(updatedPC.getGraphicsCard());
        pc.setMemory(updatedPC.getMemory());
        pc.setStorage(updatedPC.getStorage());
        pc.setCaseEntity(updatedPC.getCaseEntity());
        pc.setPowerSupply(updatedPC.getPowerSupply());

        customPCRepository.save(pc);
        return ResponseEntity.ok(pc);
    }


    @Transactional
    public void removeCustomPCFromOffer(Long offerId, Long customPCId) {
        // Dohvati ponudu
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        // Dohvati custom PC
        CustomPC customPC = customPCRepository.findById(customPCId)
                .orElseThrow(() -> new RuntimeException("Custom PC not found"));

        // Ukloni CustomPC iz ponude
        offer.getCustomPcs().remove(customPC);

        // Spremi promjenu
        offerRepository.save(offer);
    }
    @DeleteMapping("/{offerId}/{pcId}")
    public ResponseEntity<String> deletePC(@PathVariable Long offerId, @PathVariable Long pcId) {


        removeCustomPCFromOffer(offerId, pcId);


        customPCRepository.deleteById(pcId);
        return ResponseEntity.ok("PC deleted successfully");
    }
}
