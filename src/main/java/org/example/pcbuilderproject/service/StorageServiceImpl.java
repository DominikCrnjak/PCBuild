package org.example.pcbuilderproject.service;

import lombok.AllArgsConstructor;
import org.example.pcbuilderproject.domainComponents.Storage;
import org.example.pcbuilderproject.domainComponents.StorageDTO;
import org.example.pcbuilderproject.mapper.StorageMapper;
import org.example.pcbuilderproject.repository.MotherboardRepository;
import org.example.pcbuilderproject.repository.StorageRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }
    @Override
    public List<StorageDTO> getAllStorages() {
        return storageRepository.findAll()
                .stream()
                .map(StorageMapper::mapToStorageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StorageDTO getStorageById(Long id) {
        return storageRepository.findById(id)
                .map(StorageMapper::mapToStorageDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Storage not found"));
    }

    @Override
    public StorageDTO createStorage(StorageDTO storageDTO) {
        Storage storage = StorageMapper.mapToStorage(storageDTO);
        Storage savedStorage = storageRepository.save(storage);
        return StorageMapper.mapToStorageDTO(savedStorage);
    }

    @Override
    public void deleteStorage(Long id) {
        storageRepository.deleteById(id);
    }
}
