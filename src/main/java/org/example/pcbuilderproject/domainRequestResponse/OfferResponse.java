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
        this.customer_name = offer.getCustomer_name();
        this.customer_address = offer.getCustomer_address();
        this.customer_city = offer.getCustomer_city();
        this.customer_email = offer.getCustomer_email();
        this.phone_number = offer.getPhone_number();
        this.createDate = offer.getCreateDate();
        this.status = offer.getStatus();
        this.price = offer.getPrice();
        this.userId = offer.getUserId();
        this.pcs = offer.getPcs();
        this.customPcs = customPcs;
    }

}
