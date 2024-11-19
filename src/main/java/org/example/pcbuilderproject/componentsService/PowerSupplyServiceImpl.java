package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.PowerSupply;
import org.example.pcbuilderproject.componentsDomain.PowerSupplyDTO;
import org.example.pcbuilderproject.mapper.PowerSupplyMapper;
import org.example.pcbuilderproject.componentsRepository.PowerSupplyRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PowerSupplyServiceImpl implements PowerSupplyService {
    private final PowerSupplyRepository powerSupplyRepository;

    public PowerSupplyServiceImpl(PowerSupplyRepository powerSupplyRepository) {
        this.powerSupplyRepository = powerSupplyRepository;
    }
    @Override
    public List<PowerSupplyDTO> getAllPowerSupplies() {
        return powerSupplyRepository.findAll()
                .stream()
                .map(PowerSupplyMapper::mapToPowerSupplyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PowerSupplyDTO getPowerSupplyById(Long id) {
        return powerSupplyRepository.findById(id)
                .map(PowerSupplyMapper::mapToPowerSupplyDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Power Supply not found"));
    }

    @Override
    public PowerSupplyDTO createPowerSupply(PowerSupplyDTO powerSupplyDTO) {
        PowerSupply powerSupply = PowerSupplyMapper.mapToPowerSupply(powerSupplyDTO);
        PowerSupply savedPowerSupply = powerSupplyRepository.save(powerSupply);
        return PowerSupplyMapper.mapToPowerSupplyDTO(savedPowerSupply);
    }

    @Override
    public void deletePowerSupply(Long id) {
        powerSupplyRepository.deleteById(id);
    }
}
