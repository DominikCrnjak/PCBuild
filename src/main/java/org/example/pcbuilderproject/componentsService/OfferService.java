package org.example.pcbuilderproject.componentsService;

import org.example.pcbuilderproject.domain.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer createOffer(Offer offer);

    void deleteOffer(Long id);
}
