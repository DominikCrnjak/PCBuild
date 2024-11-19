package org.example.pcbuilderproject.domainRequestResponse;

import lombok.Data;
import org.example.pcbuilderproject.domain.CustomPC;
import org.example.pcbuilderproject.domain.PC;

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