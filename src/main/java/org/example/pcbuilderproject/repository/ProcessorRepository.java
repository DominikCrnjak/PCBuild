package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long> {
}