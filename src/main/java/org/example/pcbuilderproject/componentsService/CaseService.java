package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.CaseDTO;

import java.util.List;

public interface CaseService {
    List<CaseDTO> getAllCases();

    CaseDTO getCaseById(Long id);

    CaseDTO createCase(CaseDTO caseDto);

    void deleteCase(Long id);
}