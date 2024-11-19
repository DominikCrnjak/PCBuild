package org.example.pcbuilderproject.mapper;


import org.example.pcbuilderproject.componentsDomain.GraphicsCard;
import org.example.pcbuilderproject.componentsDomain.GraphicsCardDTO;

public class GraphicsCardMapper {

    public static GraphicsCardDTO mapToGraphicsCardDTO(GraphicsCard graphicsCard) {
        GraphicsCardDTO dto = new GraphicsCardDTO();
        dto.setId(graphicsCard.getId());
        dto.setName(graphicsCard.getName());
        dto.setManufacturer(graphicsCard.getManufacturer());
        dto.setSpecifications(graphicsCard.getSpecifications());
        dto.setPrice(graphicsCard.getPrice());
        return dto;
    }

    public static GraphicsCard mapToGraphicsCard(GraphicsCardDTO dto) {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(dto.getId());
        graphicsCard.setName(dto.getName());
        graphicsCard.setManufacturer(dto.getManufacturer());
        graphicsCard.setSpecifications(dto.getSpecifications());
        graphicsCard.setPrice(dto.getPrice());
        return graphicsCard;
    }
}
