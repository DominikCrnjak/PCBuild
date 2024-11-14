package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.PowerSupplyDTO;

import java.util.List;

public interface PowerSupplyService {
    List<PowerSupplyDTO> getAllPowerSupplies();

    PowerSupplyDTO getPowerSupplyById(Long id);

    PowerSupplyDTO createPowerSupply(PowerSupplyDTO powerSupply);

    void deletePowerSupply(Long id);
}
