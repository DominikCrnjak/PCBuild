package org.example.pcbuilderproject.domain;

import org.example.pcbuilderproject.mapper.*;
import org.example.pcbuilderproject.repository.*;
import org.example.pcbuilderproject.service.*;
import org.example.pcbuilderproject.user.User;
import org.example.pcbuilderproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserHasOfferRepository userOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PCRepository pcRepository;

    @Autowired
    private UserHasOfferRepository userHasOfferRepository;

    @Autowired
    private OfferHasPcRepository offerHasPcRepository;

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

    @GetMapping("/{userId}")
    public ResponseEntity<List<Offer>> getOffersByUserId(@PathVariable Long userId) {
        List<Offer> offers = offerRepository.findAll().stream()
                .filter(offer -> offer.getUserId().equals(userId)) // Filtriraj prema userId
                .collect(Collectors.toList());
        return ResponseEntity.ok(offers);
    }

    @PostMapping
    public ResponseEntity<String> createOffer(@RequestBody OfferRequest request) {
        // dohvaćanje korisnika iz sesije (SecurityContextHolder)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(username);
        // dohvaćanje korisnika iz baze
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // kreiraj novi PC
        PC pc = new PC();
        pc.setName(request.getName());

        // Dohvaćanje i postavljanje komponenti
        pc.setProcessor(ProcessorMapper.mapToProcessor(processorService.getProcessorById(request.getProcessorId())));
        pc.setMotherboard(MotherboardMapper.mapToMotherboard(motherboardService.getMotherboardById(request.getMotherboardId())));
        pc.setGraphicsCard(GraphicsCardMapper.mapToGraphicsCard(graphicsCardService.getGraphicsCardById(request.getGraphicsCardId())));
        pc.setMemory(MemoryMapper.mapToMemory(memoryService.getMemoryById(request.getMemoryId())));
        pc.setStorage(StorageMapper.mapToStorage(storageService.getStorageById(request.getStorageId())));
        pc.setCaseEntity(CaseMapper.mapToCase(caseService.getCaseById(request.getPcCaseId())));
        pc.setPowerSupply(PowerSupplyMapper.mapToPowerSupply(powerSupplyService.getPowerSupplyById(request.getPowerSupplyId())));
        // Spremanje PC-a u bazu
        pcRepository.save(pc);


        // kreiraj novi offer
        Offer offer = new Offer();
        offer.setName(user.getUsername() + "-" + pc.getName());
        offer.setCustomer_name(user.getFirstname() + " " + user.getLastname());
        offer.setCustomer_address("Ulica 12");
        offer.setCustomer_email(user.getEmail());
        offer.setPhone_number(user.getPhoneNumber());
        offer.setCreate_date(LocalDate.now().toString()); // automatski postavi datum
        offer.setStatus("pending");
        offer.setPcId(pc.getId());
        offer.setUserId(user.getId());
        offer = offerRepository.save(offer);

        // poveži offer s korisnikom
        UserHasOffer userHasOffer = new UserHasOffer();
        userHasOffer.setUserId(user.getId());
        userHasOffer.setOfferId(offer.getId());

        userHasOfferRepository.save(userHasOffer);

        // poveži offer s PC-om
        OfferHasPC offerHasPc = new OfferHasPC();
        offerHasPc.setOfferId(offer.getId());
        offerHasPc.setPcId(pc.getId());
        offerHasPcRepository.save(offerHasPc);

        return ResponseEntity.ok("Offer with PC created successfully");
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

        offer.setName(updatedOffer.getName());
        offer.setCustomer_name(updatedOffer.getCustomer_name());
        offer.setCustomer_address(updatedOffer.getCustomer_address());
        offer.setCustomer_email(updatedOffer.getCustomer_email());
        offer.setPhone_number(updatedOffer.getPhone_number());
        offer.setCreate_date(updatedOffer.getCreate_date());
        offer.setStatus(updatedOffer.getStatus());

        offerRepository.save(offer);
        return ResponseEntity.ok(offer);
    }
}