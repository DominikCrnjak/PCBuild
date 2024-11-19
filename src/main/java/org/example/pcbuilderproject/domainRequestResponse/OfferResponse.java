package org.example.pcbuilderproject.domainRequestResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.pcbuilderproject.domain.CustomPC;
import org.example.pcbuilderproject.domain.Offer;
import org.example.pcbuilderproject.domain.PC;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OfferResponse {
    private Long id;


    private String customer_name;

    private String customer_address;

    private String customer_city;

    private String customer_email;

    private String phone_number;

    private String createDate;

    private String status;

    private Long price;

    private Long userId;

    private List<PC> pcs;

    private List<CustomPC> customPcs;

    public OfferResponse(Offer offer, List<CustomPC> customPcs) {
        this.id = offer.getId();
        this.customer_name = offer.getCustomerName();
        this.customer_address = offer.getCustomerAddress();
        this.customer_city = offer.getCustomerCity();
        this.customer_email = offer.getCustomerEmail();
        this.phone_number = offer.getPhoneNumber();
        this.createDate = offer.getCreateDate();
        this.status = offer.getStatus();
        this.price = offer.getPrice();
        this.userId = offer.getUserId();
        this.pcs = offer.getPcs();
        this.customPcs = customPcs;
    }

}
