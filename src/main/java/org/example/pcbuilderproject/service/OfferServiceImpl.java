package org.example.pcbuilderproject.service;

import org.example.pcbuilderproject.domain.Offer;
import org.example.pcbuilderproject.repository.OfferRepository;
import org.example.pcbuilderproject.repository.UserHasOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserHasOfferRepository userOfferRepository;

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<Offer> getOfferByUserId(Long userId) {
        return userOfferRepository.findByUserId(userId);
    }
}
