package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.Memory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends CrudRepository<Memory, Long> {
}