package org.example.pcbuilderproject.mapper;

import org.example.pcbuilderproject.componentsDomain.Memory;
import org.example.pcbuilderproject.componentsDomain.MemoryDTO;

public class MemoryMapper {

    public static MemoryDTO mapToMemoryDTO(Memory memory) {
        MemoryDTO dto = new MemoryDTO();
        dto.setId(memory.getId());
        dto.setName(memory.getName());
        dto.setManufacturer(memory.getManufacturer());
        dto.setSpecifications(memory.getSpecifications());
        dto.setPrice(memory.getPrice());
        dto.setCapacity(memory.getCapacity());
        return dto;
    }

    public static Memory mapToMemory(MemoryDTO dto) {
        Memory memory = new Memory();
        memory.setId(dto.getId());
        memory.setName(dto.getName());
        memory.setManufacturer(dto.getManufacturer());
        memory.setSpecifications(dto.getSpecifications());
        memory.setPrice(dto.getPrice());
        memory.setCapacity(dto.getCapacity());
        return memory;
    }
}