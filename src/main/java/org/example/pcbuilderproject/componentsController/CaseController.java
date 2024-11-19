package org.example.pcbuilderproject.componentsController;

import org.example.pcbuilderproject.componentsDomain.CaseDTO;
import org.example.pcbuilderproject.componentsService.CaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case")
@CrossOrigin(origins = "http://localhost:3000")
public class CaseController {
    private final CaseServiceImpl caseService;

    @Autowired
    public CaseController(CaseServiceImpl caseService) {
        this.caseService = caseService;
    }

    @GetMapping("")
    public ResponseEntity<List<CaseDTO>> getAllCases() {
        List<CaseDTO> cases = caseService.getAllCases();
        return ResponseEntity.ok(cases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDTO> getCaseById(@PathVariable Long id) {
        CaseDTO caseDto = caseService.getCaseById(id);
        return ResponseEntity.ok(caseDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CaseDTO> createCase(@RequestBody CaseDTO caseDto) {
        CaseDTO savedCase = caseService.createCase(caseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCase);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCase(@PathVariable Long id) {
        caseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }
}
