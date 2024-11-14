package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.CaseDTO;

import java.util.List;

public interface CaseService {
    List<CaseDTO> getAllCases();

    CaseDTO getCaseById(Long id);

    CaseDTO createCase(CaseDTO caseDto);

    void deleteCase(Long id);
}