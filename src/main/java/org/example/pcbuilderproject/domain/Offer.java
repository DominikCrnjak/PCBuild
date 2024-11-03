package org.example.pcbuilderproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer_name;

    private String customer_address;

    private String customer_email;

    private String phone_number;

    private String create_date;

    private String status;

    @ManyToMany
    @JoinTable(
            name = "offer_has_pc",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "pc_id")
    )
    private List<PC> pcs;



}
