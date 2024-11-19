package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
}
