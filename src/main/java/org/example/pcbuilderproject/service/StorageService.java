package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domainComponents.StorageDTO;

import java.util.List;

public interface StorageService {
    List<StorageDTO> getAllStorages();

    StorageDTO getStorageById(Long id);

    StorageDTO createStorage(StorageDTO storage);

    void deleteStorage(Long id);
}
