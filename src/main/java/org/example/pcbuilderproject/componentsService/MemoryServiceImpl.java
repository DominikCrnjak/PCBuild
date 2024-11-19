package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.Memory;
import org.example.pcbuilderproject.componentsDomain.MemoryDTO;
import org.example.pcbuilderproject.mapper.MemoryMapper;
import org.example.pcbuilderproject.componentsRepository.MemoryRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryServiceImpl implements MemoryService {
    private final MemoryRepository memoryRepository;

    public MemoryServiceImpl(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    @Override
    public List<MemoryDTO> getAllMemories() {
        return memoryRepository.findAll()
                .stream()
                .map(MemoryMapper::mapToMemoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemoryDTO getMemoryById(Long id) {
        return memoryRepository.findById(id)
                .map(MemoryMapper::mapToMemoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Memory not found"));
    }

    @Override
    public MemoryDTO createMemory(MemoryDTO memoryDTO) {
        Memory memory = MemoryMapper.mapToMemory(memoryDTO);
        Memory savedMemory = memoryRepository.save(memory);
        return MemoryMapper.mapToMemoryDTO(savedMemory);
    }

    @Override
    public void deleteMemory(Long id) {
        memoryRepository.deleteById(id);
    }
}