package org.example.pcbuilderproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class PcDTO {

    private Integer id;
    private String name;
    private Integer processorId;
    private Integer motherboardId;
    private Integer graphicsCardId;
    private Integer memoryId;
    private Integer storageId;
    private Integer caseId;
    private Integer powerSupplyId;
    private List<Integer> offerIds;

}