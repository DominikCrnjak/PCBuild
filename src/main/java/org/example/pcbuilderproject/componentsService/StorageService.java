package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.componentsDomain.StorageDTO;

import java.util.List;

public interface StorageService {
    List<StorageDTO> getAllStorages();

    StorageDTO getStorageById(Long id);

    StorageDTO createStorage(StorageDTO storage);

    void deleteStorage(Long id);
}
