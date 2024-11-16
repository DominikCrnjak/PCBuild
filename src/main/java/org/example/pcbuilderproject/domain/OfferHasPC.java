package org.example.pcbuilderproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OfferHasPC {

    @Id
    private Long id;

    private Long offerId;
    private Long PCId;
}
