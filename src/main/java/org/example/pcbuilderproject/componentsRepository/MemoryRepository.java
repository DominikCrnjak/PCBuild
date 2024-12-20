package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
}