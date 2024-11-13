package com.ttalang.admin.commonModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchWithBicycleCount {
    private int branchId;
    private String branchStatus;
    @JsonProperty("branchName")
    private String branchName;
    @JsonProperty("bicycleCount")
    private int bicycleCount;

}
