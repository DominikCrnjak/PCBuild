package org.example.pcbuilderproject.domainController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pcbuilderproject.componentsService.*;
import org.example.pcbuilderproject.domain.*;
import org.example.pcbuilderproject.domainRepository.OfferRepository;
import org.example.pcbuilderproject.domainRepository.PCRepository;
import org.example.pcbuilderproject.domainRequestResponse.CreateOfferRequest;
import org.example.pcbuilderproject.domainRequestResponse.OfferResponse;
import org.example.pcbuilderproject.securityUser.User;
import org.example.pcbuilderproject.securityUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OfferResponse>> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        List<OfferResponse> responses = new ArrayList<>();

        for (Offer offer : offers) {
            List<CustomPC> customPCList = new ArrayList<>();
            try {
                if (offer.getCustomPcsJson() != null) {
                    customPCList = objectMapper.readValue(
                            offer.getCustomPcsJson(),
                            new TypeReference<List<CustomPC>>() {}
                    );
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error deserializing custom PCs for offer with ID: " + offer.getId(), e);
            }

            OfferResponse response = new OfferResponse(offer, customPCList);
            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        ObjectMapper objectMapper = new ObjectMapper();
        List<CustomPC> customPCList;
        try {
            customPCList = objectMapper.readValue(
                    offer.getCustomPcsJson(),
                    new TypeReference<List<CustomPC>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing custom PCs", e);
        }

        OfferResponse response = new OfferResponse(offer, customPCList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<OfferResponse>> getOffersByUserId(@PathVariable Long userId) {
        List<Offer> offers = offerRepository.findAll().stream()
                .filter(offer -> offer.getUserId().equals(userId)) // Filtriraj prema userId
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        List<OfferResponse> responses = new ArrayList<>();

        for (Offer offer : offers) {
            List<CustomPC> customPCList = new ArrayList<>();
            try {
                if (offer.getCustomPcsJson() != null) {
                    customPCList = objectMapper.readValue(
                            offer.getCustomPcsJson(),
                            new TypeReference<List<CustomPC>>() {
                            }
                    );
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error deserializing custom PCs for offer with ID: " + offer.getId(), e);
            }

            OfferResponse response = new OfferResponse(offer, customPCList);
            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Object> createOffer(@RequestBody CreateOfferRequest request) throws JsonProcessingException {
        // dohvaćanje korisnika iz sesije (SecurityContextHolder)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(username);
        // dohvaćanje korisnika iz baze
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CustomPC> customPcs = new ArrayList<>();

        // kreiraj novi offer
        Offer offer = new Offer();
        offer.setCustomer_name(request.getCustomerName());
        offer.setCustomer_address(request.getCustomerAddress());
        offer.setCustomer_email(request.getEmail());
        offer.setCustomer_city(request.getCity());
        offer.setPhone_number(request.getPhoneNumber());
        offer.setPrice(request.getPrice());
        offer.setCreateDate(LocalDate.now().toString()); // automatski postavi datum
        offer.setStatus("pending");
        offer.setUserId(user.getId());
        List<PC> pcs = request.getPcs();
        offer.setPcs(pcs);


        List<CustomPC> customPCList = request.getCustomPcs(); // Lista CustomPC iz requesta

        ObjectMapper objectMapper = new ObjectMapper();
        String customPcsJson = objectMapper.writeValueAsString(customPCList);
        System.out.println(customPcsJson);

        offer.setCustomPcsJson(customPcsJson); // Spremi CustomPC listu kao JSON string



        offerRepository.save(offer);

        OfferResponse response = new OfferResponse(offer, customPCList);

        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        offerRepository.deleteById(id);
        return ResponseEntity.ok("Offer deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse> updateOffer(@PathVariable Long id, @RequestBody CreateOfferRequest updatedOfferResponse) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setCustomer_name(updatedOfferResponse.getCustomerName());
        offer.setCustomer_address(updatedOfferResponse.getCustomerAddress());
        offer.setCustomer_email(updatedOfferResponse.getEmail());
        offer.setCustomer_city(updatedOfferResponse.getCity());
        offer.setPhone_number(updatedOfferResponse.getPhoneNumber());
        offer.setStatus(updatedOfferResponse.getStatus());
        offer.setPrice(updatedOfferResponse.getPrice());
        List<PC> pcs = updatedOfferResponse.getPcs();
        offer.setPcs(pcs);

        // Pretvori listu CustomPC objekata u JSON string i postavi u Offer
        if (updatedOfferResponse.getCustomPcs() != null && !updatedOfferResponse.getCustomPcs().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String customPcsJson = objectMapper.writeValueAsString(updatedOfferResponse.getCustomPcs());
                offer.setCustomPcsJson(customPcsJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing custom PCs", e);
            }
        } else {
            offer.setCustomPcsJson(null); // Ako nema CustomPC objekata, postavi na null
        }

        // Spremi ažurirani Offer u bazu
        Offer savedOffer = offerRepository.save(offer);

        // Transformiraj Offer natrag u OfferResponse za odgovor
        List<CustomPC> customPCList = new ArrayList<>();
        try {
            if (savedOffer.getCustomPcsJson() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                customPCList = objectMapper.readValue(
                        savedOffer.getCustomPcsJson(),
                        new TypeReference<List<CustomPC>>() {}
                );
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing custom PCs", e);
        }

        OfferResponse response = new OfferResponse(savedOffer, customPCList);
        return ResponseEntity.ok(response);
    }

}