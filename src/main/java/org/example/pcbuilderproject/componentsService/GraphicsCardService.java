package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.GraphicsCardDTO;

import java.util.List;

public interface GraphicsCardService {
    List<GraphicsCardDTO> getAllGraphicsCards();

    GraphicsCardDTO getGraphicsCardById(Long id);

    GraphicsCardDTO createGraphicsCard(GraphicsCardDTO graphicsCard);

    void deleteGraphicsCard(Long id);
}
