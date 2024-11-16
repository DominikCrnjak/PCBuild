package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domain.Offer;
import org.example.pcbuilderproject.domain.UserHasOffer;
import org.example.pcbuilderproject.domainComponents.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHasOfferRepository  extends JpaRepository<UserHasOffer,Long> {
    List<Offer> findByUserId(Long userId);
}

