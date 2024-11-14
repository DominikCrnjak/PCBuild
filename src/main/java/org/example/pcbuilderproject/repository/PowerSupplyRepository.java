package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply,Long> {
}
