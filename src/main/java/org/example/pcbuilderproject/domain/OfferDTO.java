package org.example.pcbuilderproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class OfferDTO {

    private Long id;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String phoneNumber;
    private String createDate;
    private String status;
    private List<Integer> pcIds;

}