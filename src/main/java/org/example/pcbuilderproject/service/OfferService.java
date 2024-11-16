package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domain.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer createOffer(Offer offer);
    List<Offer> getOfferByUserId(Long userId);

    void deleteOffer(Long id);
}
