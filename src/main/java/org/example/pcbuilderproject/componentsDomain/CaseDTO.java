package org.example.pcbuilderproject.componentsDomain;

import lombok.Data;

@Data
public class CaseDTO {
    private Long id;

    private String name;
    private String manufacturer;
    private double price;
    private String specifications;
}
