package org.example.pcbuilderproject.domainComponents;

import lombok.Data;

@Data
public class MotherboardDTO {
    private Long id;

    private String name;
    private String manufacturer;
    private double price;
    private String specifications;
    private String socket;
}
