package org.example.pcbuilderproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class CreateOfferRequest {
    private String customerName;
    private String customerAddress;
    private String city;
    private String email;
    private String phoneNumber;

    private List<Long> pcIds; // Lista ID-eva gotovih PC-ova
    private List<CustomPCRequest> customPcs; // Lista komponenti za svaki custom PC

    // Getters i setters
}