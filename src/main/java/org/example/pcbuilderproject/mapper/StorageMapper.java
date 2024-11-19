package org.example.pcbuilderproject.mapper;

import org.example.pcbuilderproject.componentsDomain.Storage;
import org.example.pcbuilderproject.componentsDomain.StorageDTO;

public class StorageMapper {

    public static StorageDTO mapToStorageDTO(Storage storage) {
        StorageDTO dto = new StorageDTO();
        dto.setId(storage.getId());
        dto.setName(storage.getName());
        dto.setManufacturer(storage.getManufacturer());
        dto.setSpecifications(storage.getSpecifications());
        dto.setPrice(storage.getPrice());
        dto.setCapacity(storage.getCapacity());
        return dto;
    }

    public static Storage mapToStorage(StorageDTO dto) {
        Storage storage = new Storage();
        storage.setId(dto.getId());
        storage.setName(dto.getName());
        storage.setManufacturer(dto.getManufacturer());
        storage.setSpecifications(dto.getSpecifications());
        storage.setPrice(dto.getPrice());
        storage.setCapacity(dto.getCapacity());
        return storage;
    }
}