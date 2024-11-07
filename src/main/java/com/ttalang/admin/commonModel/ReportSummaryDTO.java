package com.ttalang.admin.commonModel;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReportSummaryDTO {
    private int reportId;
    private String bicycleName;
    private LocalDateTime reportDate;
    private String categoryName;
    private String reportDetails;

    public ReportSummaryDTO(int reportId, String bicycleName, LocalDateTime reportDate, String categoryName, String reportDetails) {
        this.reportId = reportId;
        this.bicycleName = bicycleName;
        this.reportDate = reportDate;
        this.categoryName = categoryName;
        this.reportDetails = reportDetails;
    }
}
