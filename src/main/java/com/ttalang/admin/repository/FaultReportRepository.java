package com.ttalang.admin.repository;

import com.ttalang.admin.commonModel.FaultReport;
import com.ttalang.admin.commonModel.ReportSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FaultReportRepository extends JpaRepository<FaultReport, Integer> {

    @Query(
        "SELECT new com.ttalang.admin.commonModel.ReportSummaryDTO(r.reportId, b.bicycleName, r.reportDate, c.categoryName, r.reportDetails) "
            +
            "FROM FaultReport r " +
            "JOIN Bicycle b ON r.bicycleId = b.bicycleId " +
            "JOIN FaultCategory c ON r.categoryId = c.categoryId " +
            "WHERE r.reportStatus = '0' AND r.bicycleId = :bicycleId")
    ReportSummaryDTO findReportedBicycles(@Param("bicycleId") int bicycleId);

    FaultReport findByReportId(int reportId);
}
