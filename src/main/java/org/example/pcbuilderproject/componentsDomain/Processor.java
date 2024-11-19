package org.example.pcbuilderproject.componentsDomain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "processor")
public class Processor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;
    private double price;
    private String specifications;

    private int cores;
    private double clockSpeed;
    private String socket;
}