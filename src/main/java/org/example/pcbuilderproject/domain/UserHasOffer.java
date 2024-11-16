package org.example.pcbuilderproject.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_has_offer")
public class UserHasOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long offerId;



}
