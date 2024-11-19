package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.Case;
import org.example.pcbuilderproject.componentsDomain.CaseDTO;
import org.example.pcbuilderproject.mapper.CaseMapper;
import org.example.pcbuilderproject.componentsRepository.PCCaseRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseServiceImpl implements CaseService {
    private final PCCaseRepository caseRepository;

    public CaseServiceImpl(PCCaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<CaseDTO> getAllCases() {
        return caseRepository.findAll()
                .stream()
                .map(CaseMapper::mapToCaseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CaseDTO getCaseById(Long id) {
        return caseRepository.findById(id)
                .map(CaseMapper::mapToCaseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));
    }

    @Override
    public CaseDTO createCase(CaseDTO caseDTO) {
        Case caseEntity = CaseMapper.mapToCase(caseDTO);
        Case savedCase = caseRepository.save(caseEntity);
        return CaseMapper.mapToCaseDTO(savedCase);
    }

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}