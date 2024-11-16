package org.example.pcbuilderproject.domain;

import lombok.Data;

@Data
public class OfferRequest {

    private String name;
    private Long processorId;
    private Long motherboardId;
    private Long graphicsCardId;
    private Long memoryId;
    private Long storageId;
    private Long pcCaseId;
    private Long powerSupplyId;

}
