package org.example.pcbuilderproject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offer_has_pc")
public class OfferHasPC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long offerId;
    private Long pcId;
}
