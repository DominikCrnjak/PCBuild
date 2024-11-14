package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
}
