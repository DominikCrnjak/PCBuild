package org.example.pcbuilderproject.componentsDomain;

import lombok.Data;

@Data
public class StorageDTO {
    private Long id;

    private String name;
    private String manufacturer;
    private double price;
    private String specifications;
    private int capacity;
}
