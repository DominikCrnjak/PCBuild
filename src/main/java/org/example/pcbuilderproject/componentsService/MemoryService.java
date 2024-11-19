package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.MemoryDTO;

import java.util.List;

public interface MemoryService {
    List<MemoryDTO> getAllMemories();

    MemoryDTO getMemoryById(Long id);

    MemoryDTO createMemory(MemoryDTO memory);

    void deleteMemory(Long id);
}
