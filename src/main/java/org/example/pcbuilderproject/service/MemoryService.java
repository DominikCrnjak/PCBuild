package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.MemoryDTO;

import java.util.List;

public interface MemoryService {
    List<MemoryDTO> getAllMemories();

    MemoryDTO getMemoryById(Long id);

    MemoryDTO createMemory(MemoryDTO memory);

    void deleteMemory(Long id);
}
