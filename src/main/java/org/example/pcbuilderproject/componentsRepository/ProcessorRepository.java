package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long> {
}