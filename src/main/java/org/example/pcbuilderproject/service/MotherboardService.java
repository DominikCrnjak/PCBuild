package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.MotherboardDTO;

import java.util.List;

public interface MotherboardService {
    List<MotherboardDTO> getAllMotherboards();

    MotherboardDTO getMotherboardById(Long id);

    MotherboardDTO createMotherboard(MotherboardDTO motherboard);

    void deleteMotherboard(Long id);
}