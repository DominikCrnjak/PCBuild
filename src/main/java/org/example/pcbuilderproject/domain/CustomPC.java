package org.example.pcbuilderproject.domain;

import lombok.Data;
import org.example.pcbuilderproject.componentsDomain.*;



@Data
public class CustomPC {

    private Long id;

    private String name;

    private Long price;

    private Processor processor;

    private Motherboard motherboard;

    private GraphicsCard graphicsCard;

    private Memory memory;

    private Storage storage;

    private Case caseEntity;

    private PowerSupply powerSupply;
}
