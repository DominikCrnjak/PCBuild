package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
}
