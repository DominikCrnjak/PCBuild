package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.MotherboardDTO;

import java.util.List;

public interface MotherboardService {
    List<MotherboardDTO> getAllMotherboards();

    MotherboardDTO getMotherboardById(Long id);

    MotherboardDTO createMotherboard(MotherboardDTO motherboard);

    void deleteMotherboard(Long id);
}