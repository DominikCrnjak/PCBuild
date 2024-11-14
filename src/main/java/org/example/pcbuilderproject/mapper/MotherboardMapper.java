package org.example.pcbuilderproject.mapper;

import org.example.pcbuilderproject.domainComponents.Motherboard;
import org.example.pcbuilderproject.domainComponents.MotherboardDTO;

public class MotherboardMapper {

    public static MotherboardDTO mapToMotherboardDTO(Motherboard motherboard) {
        MotherboardDTO dto = new MotherboardDTO();
        dto.setId(motherboard.getId());
        dto.setName(motherboard.getName());
        dto.setManufacturer(motherboard.getManufacturer());
        dto.setSpecifications(motherboard.getSpecifications());
        dto.setPrice(motherboard.getPrice());
        dto.setSocket(motherboard.getSocket());
        return dto;
    }

    public static Motherboard mapToMotherboard(MotherboardDTO dto) {
        Motherboard motherboard = new Motherboard();
        motherboard.setId(dto.getId());
        motherboard.setName(dto.getName());
        motherboard.setManufacturer(dto.getManufacturer());
        motherboard.setSpecifications(dto.getSpecifications());
        motherboard.setPrice(dto.getPrice());
        motherboard.setSocket(dto.getSocket());
        return motherboard;
    }
}
