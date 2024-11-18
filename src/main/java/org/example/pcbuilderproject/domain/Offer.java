package org.example.pcbuilderproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String customer_name;

    private String customer_address;

    private String customer_city;

    private String customer_email;

    private String phone_number;

    private String createDate;

    private String status;

    private Long price;

    private Long userId;

    @ManyToMany
    @JoinTable(
            name = "offer_has_pc", // Povezujuća tablica
            joinColumns = @JoinColumn(name = "offer_id"), // Ključ iz offer
            inverseJoinColumns = @JoinColumn(name = "pc_id") // Ključ iz pc
    )
    private List<PC> pcs = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "offer_has_custom", // Povezujuća tablica
            joinColumns = @JoinColumn(name = "offer_id"), // Ključ iz offer
            inverseJoinColumns = @JoinColumn(name = "custompc_id") // Ključ iz pc
    )
    private List<CustomPC> customPcs = new ArrayList<>();




}
