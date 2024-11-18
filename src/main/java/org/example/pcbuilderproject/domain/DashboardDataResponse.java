package org.example.pcbuilderproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDataResponse {
    private long totalOffers;
    private long offersDone;
    private long offersPending;
    private long offersRejected;
    private List<Offer> lastOffers;
}