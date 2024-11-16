package org.example.pcbuilderproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.example.pcbuilderproject.domainComponents.*;

import java.util.List;

@Entity
@Data
public class PC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "processor", referencedColumnName = "id")
    private Processor processor;

    @ManyToOne
    @JoinColumn(name = "motherboard", referencedColumnName = "id")
    private Motherboard motherboard;

    @ManyToOne
    @JoinColumn(name = "graphics_card", referencedColumnName = "id")
    private GraphicsCard graphicsCard;

    @ManyToOne
    @JoinColumn(name = "memory", referencedColumnName = "id")
    private Memory memory;

    @ManyToOne
    @JoinColumn(name = "storage", referencedColumnName = "id")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "pc_case", referencedColumnName = "id")
    private Case caseEntity;

    @ManyToOne
    @JoinColumn(name = "power_supply", referencedColumnName = "id")
    private PowerSupply powerSupply;


}