package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
}