package org.example.pcbuilderproject.controller;

import org.example.pcbuilderproject.domain.DashboardDataResponse;
import org.example.pcbuilderproject.domain.Offer;
import org.example.pcbuilderproject.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        // Kreiraj odgovor
        DashboardDataResponse response = new DashboardDataResponse(
                totalOffers,
                offersDone,
                offersPending,
                offersRejected,
                lastOffers
        );

        return ResponseEntity.ok(response);
    }
}