package org.example.pcbuilderproject.domain;

import lombok.Data;

@Data
public class CustomPCRequest {
    private String name; // Ime custom PC-a
    private Long cpuId;
    private Long motherboardId;
    private Long ramId;
    private Long gpuId;
    private Long pcCaseId;
    private Long powerSupplyId;
    private Long storageId;

}