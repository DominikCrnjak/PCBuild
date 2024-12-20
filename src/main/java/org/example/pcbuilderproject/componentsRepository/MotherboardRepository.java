package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
}
