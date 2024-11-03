package org.example.pcbuilderproject.mapper;


import org.example.pcbuilderproject.domainComponents.Case;
import org.example.pcbuilderproject.domainComponents.CaseDTO;

public class CaseMapper {

    public static CaseDTO mapToCaseDTO(Case caseEntity) {
        CaseDTO dto = new CaseDTO();
        dto.setId(caseEntity.getId());
        dto.setName(caseEntity.getName());
        dto.setManufacturer(caseEntity.getManufacturer());
        dto.setSpecifications(caseEntity.getSpecifications());
        dto.setPrice(caseEntity.getPrice());
        return dto;
    }

    public static Case mapToCase(CaseDTO dto) {
        Case caseEntity = new Case();
        caseEntity.setId(dto.getId());
        caseEntity.setName(dto.getName());
        caseEntity.setManufacturer(dto.getManufacturer());
        caseEntity.setSpecifications(dto.getSpecifications());
        caseEntity.setPrice(dto.getPrice());
        return caseEntity;
    }
}