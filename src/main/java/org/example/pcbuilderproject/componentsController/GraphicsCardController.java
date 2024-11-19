package org.example.pcbuilderproject.componentsController;

import org.example.pcbuilderproject.componentsDomain.GraphicsCardDTO;
import org.example.pcbuilderproject.componentsService.GraphicsCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graphics-card")
@CrossOrigin(origins = "http://localhost:3000")
public class GraphicsCardController {
    private final GraphicsCardServiceImpl graphicsCardService;

    @Autowired
    public GraphicsCardController(GraphicsCardServiceImpl graphicsCardService) {
        this.graphicsCardService = graphicsCardService;
    }

    @GetMapping("")
    public ResponseEntity<List<GraphicsCardDTO>> getAllGraphicsCards() {
        List<GraphicsCardDTO> graphicsCards = graphicsCardService.getAllGraphicsCards();
        return ResponseEntity.ok(graphicsCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GraphicsCardDTO> getGraphicsCardById(@PathVariable Long id) {
        GraphicsCardDTO graphicsCard = graphicsCardService.getGraphicsCardById(id);
        return ResponseEntity.ok(graphicsCard);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GraphicsCardDTO> createGraphicsCard(@RequestBody GraphicsCardDTO graphicsCard) {
        GraphicsCardDTO savedGraphicsCard = graphicsCardService.createGraphicsCard(graphicsCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGraphicsCard);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGraphicsCard(@PathVariable Long id) {
        graphicsCardService.deleteGraphicsCard(id);
        return ResponseEntity.noContent().build();
    }
}
