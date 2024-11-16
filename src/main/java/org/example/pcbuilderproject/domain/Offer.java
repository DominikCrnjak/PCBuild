package org.example.pcbuilderproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String customer_name;

    private String customer_address;

    private String customer_email;

    private String phone_number;

    private String create_date;

    private String status;

    private Long pcId;

    private Long userId;


}
