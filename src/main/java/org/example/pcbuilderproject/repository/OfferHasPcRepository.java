package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domain.OfferHasPC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferHasPcRepository extends JpaRepository<OfferHasPC,Long> {
}
