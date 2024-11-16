package org.example.pcbuilderproject.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserHasOffer {

    @Id
    private Long id;

    private Long userId;
    private Long offerId;



}
