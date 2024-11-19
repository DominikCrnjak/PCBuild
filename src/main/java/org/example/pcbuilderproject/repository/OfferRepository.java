package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    // Brojanje ponuda po statusu
    long countByStatus(String status);

    // Dohvaćanje zadnjih 5 ponuda po datumu stvaranja
    List<Offer> findTop5ByOrderByCreateDateDesc();

}
