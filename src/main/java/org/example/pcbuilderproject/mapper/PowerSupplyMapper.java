package org.example.pcbuilderproject.mapper;

import org.example.pcbuilderproject.componentsDomain.PowerSupply;
import org.example.pcbuilderproject.componentsDomain.PowerSupplyDTO;

public class PowerSupplyMapper {

    public static PowerSupplyDTO mapToPowerSupplyDTO(PowerSupply powerSupply) {
        PowerSupplyDTO dto = new PowerSupplyDTO();
        dto.setId(powerSupply.getId());
        dto.setName(powerSupply.getName());
        dto.setManufacturer(powerSupply.getManufacturer());
        dto.setSpecifications(powerSupply.getSpecifications());
        dto.setPrice(powerSupply.getPrice());
        return dto;
    }

    public static PowerSupply mapToPowerSupply(PowerSupplyDTO dto) {
        PowerSupply powerSupply = new PowerSupply();
        powerSupply.setId(dto.getId());
        powerSupply.setName(dto.getName());
        powerSupply.setManufacturer(dto.getManufacturer());
        powerSupply.setSpecifications(dto.getSpecifications());
        powerSupply.setPrice(dto.getPrice());
        return powerSupply;
    }
}
