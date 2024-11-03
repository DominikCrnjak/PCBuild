package org.example.pcbuilderproject.mapper;

import org.example.pcbuilderproject.domainComponents.Processor;
import org.example.pcbuilderproject.domainComponents.ProcessorDTO;

public class ProcessorMapper {


    public static ProcessorDTO mapToProcessorDTO(Processor processor) {
        ProcessorDTO dto = new ProcessorDTO();
        dto.setId(processor.getId());
        dto.setPrice(processor.getPrice());
        dto.setName(processor.getName());
        dto.setManufacturer(processor.getManufacturer());
        dto.setSpecifications(processor.getSpecifications());
        dto.setCores(processor.getCores());
        dto.setClockSpeed(processor.getClockSpeed());
        dto.setSocket(processor.getSocket());
        return dto;
    }


    public static Processor mapToProcessor(ProcessorDTO dto) {
        Processor processor = new Processor();
        processor.setId(dto.getId());
        processor.setPrice(dto.getPrice());
        processor.setName(dto.getName());
        processor.setManufacturer(dto.getManufacturer());
        processor.setSpecifications(dto.getSpecifications());
        processor.setCores(dto.getCores());
        processor.setClockSpeed(dto.getClockSpeed());
        processor.setSocket(dto.getSocket());
        return processor;
    }
}