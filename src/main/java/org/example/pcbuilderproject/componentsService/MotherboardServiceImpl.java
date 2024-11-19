package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.Motherboard;
import org.example.pcbuilderproject.componentsDomain.MotherboardDTO;
import org.example.pcbuilderproject.mapper.MotherboardMapper;
import org.example.pcbuilderproject.componentsRepository.MotherboardRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotherboardServiceImpl implements MotherboardService {
    private final MotherboardRepository motherboardRepository;

    public MotherboardServiceImpl(MotherboardRepository motherboardRepository) {
        this.motherboardRepository = motherboardRepository;
    }
    @Override
    public List<MotherboardDTO> getAllMotherboards() {
        return motherboardRepository.findAll()
                .stream()
                .map(MotherboardMapper::mapToMotherboardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MotherboardDTO getMotherboardById(Long id) {
        return motherboardRepository.findById(id)
                .map(MotherboardMapper::mapToMotherboardDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Motherboard not found"));
    }

    @Override
    public MotherboardDTO createMotherboard(MotherboardDTO motherboardDTO) {
        Motherboard motherboard = MotherboardMapper.mapToMotherboard(motherboardDTO);
        Motherboard savedMotherboard = motherboardRepository.save(motherboard);
        return MotherboardMapper.mapToMotherboardDTO(savedMotherboard);
    }

    @Override
    public void deleteMotherboard(Long id) {
        motherboardRepository.deleteById(id);
    }
}