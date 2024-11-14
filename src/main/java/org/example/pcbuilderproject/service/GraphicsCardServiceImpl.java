package org.example.pcbuilderproject.service;

import lombok.AllArgsConstructor;
import org.example.pcbuilderproject.domainComponents.GraphicsCard;
import org.example.pcbuilderproject.domainComponents.GraphicsCardDTO;
import org.example.pcbuilderproject.mapper.GraphicsCardMapper;
import org.example.pcbuilderproject.repository.GraphicsCardRepository;
import org.example.pcbuilderproject.repository.PCCaseRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GraphicsCardServiceImpl implements GraphicsCardService {
    private final GraphicsCardRepository graphicsCardRepository;

    public GraphicsCardServiceImpl(GraphicsCardRepository graphicsCardRepository) {
        this.graphicsCardRepository = graphicsCardRepository;
    }

    @Override
    public List<GraphicsCardDTO> getAllGraphicsCards() {
        return graphicsCardRepository.findAll()
                .stream()
                .map(GraphicsCardMapper::mapToGraphicsCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GraphicsCardDTO getGraphicsCardById(Long id) {
        return graphicsCardRepository.findById(id)
                .map(GraphicsCardMapper::mapToGraphicsCardDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Graphics Card not found"));
    }

    @Override
    public GraphicsCardDTO createGraphicsCard(GraphicsCardDTO graphicsCardDTO) {
        GraphicsCard graphicsCard = GraphicsCardMapper.mapToGraphicsCard(graphicsCardDTO);
        GraphicsCard savedGraphicsCard = graphicsCardRepository.save(graphicsCard);
        return GraphicsCardMapper.mapToGraphicsCardDTO(savedGraphicsCard);
    }

    @Override
    public void deleteGraphicsCard(Long id) {
        graphicsCardRepository.deleteById(id);
    }
}
