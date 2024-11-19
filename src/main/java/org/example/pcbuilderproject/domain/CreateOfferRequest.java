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
    private String status;
    private Long price;

    private List<PC> pcs; // Lista ID-eva gotovih PC-ova
    private List<CustomPC> customPcs; // Lista komponenti za svaki custom PC


}