package org.example.pcbuilderproject.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pcbuilderproject.mapper.*;
import org.example.pcbuilderproject.repository.*;
import org.example.pcbuilderproject.service.*;
import org.example.pcbuilderproject.user.User;
import org.example.pcbuilderproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PCRepository pcRepository;

    @Autowired
    private CustomPCRepository customPCRepository;

    @Autowired
    private CaseServiceImpl caseService;

    @Autowired
    private ProcessorServiceImpl processorService;

    @Autowired
    private MotherboardServiceImpl motherboardService;

    @Autowired
    private GraphicsCardServiceImpl graphicsCardService;

    @Autowired
    private MemoryServiceImpl memoryService;

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private PowerSupplyServiceImpl powerSupplyService;

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Offer>> getOffersByUserId(@PathVariable Long userId) {
        List<Offer> offers = offerRepository.findAll().stream()
                .filter(offer -> offer.getUserId().equals(userId)) // Filtriraj prema userId
                .collect(Collectors.toList());
        return ResponseEntity.ok(offers);
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody CreateOfferRequest request) {
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
        offer.setPhone_number(request.getPhoneNumber());
        offer.setCreateDate(LocalDate.now().toString()); // automatski postavi datum
        offer.setStatus("pending");
        offer.setUserId(user.getId());
        List<PC> pcs = pcRepository.findAllById(request.getPcIds());
        offer.setPcs(pcs);


        if (request.getCustomPcs() != null && !request.getCustomPcs().isEmpty()) {
            for (CustomPCRequest customPCRequest : request.getCustomPcs()) {
                CustomPC customPC = new CustomPC();

                customPC.setProcessor(ProcessorMapper.mapToProcessor(processorService.getProcessorById(customPCRequest.getCpuId())));
                customPC.setMotherboard(MotherboardMapper.mapToMotherboard(motherboardService.getMotherboardById(customPCRequest.getMotherboardId())));
                customPC.setMemory(MemoryMapper.mapToMemory(memoryService.getMemoryById(customPCRequest.getRamId())));
                customPC.setGraphicsCard(GraphicsCardMapper.mapToGraphicsCard(graphicsCardService.getGraphicsCardById(customPCRequest.getGpuId())));
                customPC.setCaseEntity(CaseMapper.mapToCase(caseService.getCaseById(customPCRequest.getPcCaseId())));
                customPC.setPowerSupply(PowerSupplyMapper.mapToPowerSupply(powerSupplyService.getPowerSupplyById(customPCRequest.getPowerSupplyId())));
                customPC.setStorage(StorageMapper.mapToStorage(storageService.getStorageById(customPCRequest.getStorageId())));


                Long totalPrice = (long) (
                        customPC.getProcessor().getPrice() +
                                customPC.getMotherboard().getPrice() +
                                customPC.getMemory().getPrice() +
                                customPC.getGraphicsCard().getPrice() +
                                customPC.getCaseEntity().getPrice() +
                                customPC.getPowerSupply().getPrice() +
                                customPC.getStorage().getPrice()
                );

                // Set the total price to the custom PC
                customPC.setPrice(totalPrice);

                // Dodaj custom PC u listu
                customPcs.add(customPC);
                customPCRepository.save(customPC);
            }
        }

            // Postavi custom PC listu u Offer (ne treba JSON serijalizacija)
            offer.setCustomPcs(customPcs);


        offerRepository.save(offer);

        return ResponseEntity.ok(offer);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        offerRepository.deleteById(id);
        return ResponseEntity.ok("Offer deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer updatedOffer) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setCustomer_name(updatedOffer.getCustomer_name());
        offer.setCustomer_address(updatedOffer.getCustomer_address());
        offer.setCustomer_email(updatedOffer.getCustomer_email());
        offer.setPhone_number(updatedOffer.getPhone_number());
        offer.setStatus(updatedOffer.getStatus());

        offerRepository.save(offer);
        return ResponseEntity.ok(offer);
    }
}