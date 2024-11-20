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
    public ResponseEntity<List<OfferResponse>> getAllOffers() throws JsonProcessingException {
        List<Offer> offers = offerRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        List<OfferResponse> responses = new ArrayList<>();




        for (Offer offer : offers) {

            List<CustomPC> customPCList = new ArrayList<>();
            if (offer.getCustomPcsJson() == null || offer.getCustomPcsJson().isEmpty()) {
                customPCList = new ArrayList<>();
            } else {
                customPCList = objectMapper.readValue(
                        offer.getCustomPcsJson(),
                        new TypeReference<List<CustomPC>>() {}
                );
            }

            OfferResponse response = new OfferResponse(offer, customPCList);
            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable Long id) throws JsonProcessingException {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        List<CustomPC> customPCList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        if (offer.getCustomPcsJson() == null || offer.getCustomPcsJson().isEmpty()) {
            customPCList = new ArrayList<>();
        } else {
            customPCList = objectMapper.readValue(
                    offer.getCustomPcsJson(),
                    new TypeReference<List<CustomPC>>() {}
            );
        }

        OfferResponse response = new OfferResponse(offer, customPCList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<OfferResponse>> getOffersByUserId(@PathVariable Long userId) throws JsonProcessingException {
        List<Offer> offers = offerRepository.findAll().stream()
                .filter(offer -> offer.getUserId().equals(userId)) // Filtriraj prema userId
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        List<OfferResponse> responses = new ArrayList<>();

        for (Offer offer : offers) {
            List<CustomPC> customPCList = new ArrayList<>();
            if (offer.getCustomPcsJson() == null || offer.getCustomPcsJson().isEmpty()) {
                customPCList = new ArrayList<>();
            } else {
                customPCList = objectMapper.readValue(
                        offer.getCustomPcsJson(),
                        new TypeReference<List<CustomPC>>() {}
                );
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

        System.out.println(request);
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
        try {
            for (int i = 0; i < customPCList.size(); i++) {
                customPCList.get(i).setId((long) i + 1); // Dodijeli ID od 1 nadalje
            }
            // Pretvori listu u JSON string
            String customPcsJson = objectMapper.writeValueAsString(customPCList);
            offer.setCustomPcsJson(customPcsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing custom PCs", e);
        }


        offerRepository.save(offer);

        OfferResponse response = new OfferResponse(offer, customPCList);


        System.out.println(response);
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

        if (updatedOfferResponse.getCustomerName() != null) {
            offer.setCustomer_name(updatedOfferResponse.getCustomerName());
        }
        if (updatedOfferResponse.getCustomerAddress() != null) {
            offer.setCustomer_address(updatedOfferResponse.getCustomerAddress());
        }
        if (updatedOfferResponse.getEmail() != null) {
            offer.setCustomer_email(updatedOfferResponse.getEmail());
        }
        if (updatedOfferResponse.getCity() != null) {
            offer.setCustomer_city(updatedOfferResponse.getCity());
        }
        if (updatedOfferResponse.getPhoneNumber() != null) {
            offer.setPhone_number(updatedOfferResponse.getPhoneNumber());
        }
        if (updatedOfferResponse.getStatus() != null) {
            offer.setStatus(updatedOfferResponse.getStatus());
        }
        if (updatedOfferResponse.getPrice() != null) {
            offer.setPrice(updatedOfferResponse.getPrice());
        }
        List<PC> pcs = updatedOfferResponse.getPcs();
        offer.setPcs(pcs);


        List<CustomPC> customPCList = updatedOfferResponse.getCustomPcs();
        // Pretvori listu CustomPC objekata u JSON string i postavi u Offer
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (int i = 0; i < customPCList.size(); i++) {
                customPCList.get(i).setId((long) i + 1); // Dodijeli ID od 1 nadalje
            }
            // Pretvori listu u JSON string
            String customPcsJson = objectMapper.writeValueAsString(customPCList);
            offer.setCustomPcsJson(customPcsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing custom PCs", e);
        }

        // Spremi ažurirani Offer u bazu
        Offer savedOffer = offerRepository.save(offer);


        OfferResponse response = new OfferResponse(savedOffer, updatedOfferResponse.getCustomPcs());
        return ResponseEntity.ok(response);
    }

}