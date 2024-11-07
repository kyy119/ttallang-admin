package com.ttalang.admin.service;

import com.ttalang.admin.commonModel.Bicycle;
import com.ttalang.admin.commonModel.FaultReport;
import com.ttalang.admin.commonModel.ReportSummaryDTO;
import com.ttalang.admin.repository.BicycleRepository;
import com.ttalang.admin.repository.FaultReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FaultReportService {
    private final FaultReportRepository faultReportRepository;
    private final BicycleRepository bicycleRepository;
    public FaultReportService(FaultReportRepository faultReportRepository, BicycleRepository bicycleRepository){
        this.faultReportRepository = faultReportRepository;
        this.bicycleRepository = bicycleRepository;
    }
    public ReportSummaryDTO findReportedBicycles(int bicycleId){
        return faultReportRepository.findReportedBicycles(bicycleId);
    }
    @Transactional
    public FaultReport updateReport(Integer bicycleId,  Integer reportId){
        FaultReport faultReport = faultReportRepository.findByReportId(reportId);
        faultReport.setReportStatus("1");
        Bicycle bicycle = bicycleRepository.findByBicycleId(bicycleId);
        bicycle.setBicycleStatus("1");
        return faultReport;
    }
}
