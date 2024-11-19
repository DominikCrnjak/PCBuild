package org.example.pcbuilderproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String customerName;

    private String customerAddress;

    private String customerCity;

    private String customerEmail;

    private String phoneNumber;

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

    @Column(columnDefinition = "json") // Označi stupac kao JSON tip
    @ColumnTransformer(write = "?::json")
    private String customPcsJson; // JSON string za pohranu CustomPC liste






}
