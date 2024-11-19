package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.PowerSupplyDTO;

import java.util.List;

public interface PowerSupplyService {
    List<PowerSupplyDTO> getAllPowerSupplies();

    PowerSupplyDTO getPowerSupplyById(Long id);

    PowerSupplyDTO createPowerSupply(PowerSupplyDTO powerSupply);

    void deletePowerSupply(Long id);
}
