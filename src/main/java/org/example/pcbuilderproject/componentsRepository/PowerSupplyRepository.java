package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply,Long> {
}
