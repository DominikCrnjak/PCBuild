package org.example.pcbuilderproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pcbuilderproject.domain.CustomPC;
import org.example.pcbuilderproject.domain.DashboardDataResponse;
import org.example.pcbuilderproject.domain.Offer;
import org.example.pcbuilderproject.domain.OfferResponse;
import org.example.pcbuilderproject.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("")
    public ResponseEntity<DashboardDataResponse> getDashboardData() {
        // Ukupan broj ponuda
        long totalOffers = offerRepository.count();

        // Broj ponuda sa statusom "done"
        long offersDone = offerRepository.countByStatus("done");

        // Broj ponuda sa statusom "pending"
        long offersPending = offerRepository.countByStatus("pending");

        // Broj ponuda sa statusom "rejected"
        long offersRejected = offerRepository.countByStatus("rejected");

        // Zadnjih 5 ponuda (sortirano po datumu stvaranja, najnovije prvo)
        List<Offer> lastOffers = offerRepository.findTop5ByOrderByCreateDateDesc();

        ObjectMapper objectMapper = new ObjectMapper();
        List<OfferResponse> lastOffersResponse = lastOffers.stream().map(offer -> {
            List<CustomPC> customPCList;
            try {
                customPCList = objectMapper.readValue(
                        offer.getCustomPcsJson(),
                        new TypeReference<List<CustomPC>>() {}
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error deserializing custom PCs", e);
            }
            return new OfferResponse(offer, customPCList);
        }).collect(Collectors.toList());

        // Kreiraj odgovor
        DashboardDataResponse response = new DashboardDataResponse(
                totalOffers,
                offersDone,
                offersPending,
                offersRejected,
                lastOffersResponse
        );

        return ResponseEntity.ok(response);
    }
}