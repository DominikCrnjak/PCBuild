package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.GraphicsCardDTO;

import java.util.List;

public interface GraphicsCardService {
    List<GraphicsCardDTO> getAllGraphicsCards();

    GraphicsCardDTO getGraphicsCardById(Long id);

    GraphicsCardDTO createGraphicsCard(GraphicsCardDTO graphicsCard);

    void deleteGraphicsCard(Long id);
}
