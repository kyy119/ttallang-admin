package com.ttalang.admin.service;


import com.ttalang.admin.commonModel.Bicycle;
import com.ttalang.admin.commonModel.Branch;
import com.ttalang.admin.repository.BicycleRepository;
import com.ttalang.admin.repository.BranchRepository;
import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BicycleService {

    private final BicycleRepository bicycleRepository;
    private final BranchRepository branchRepository;

    public BicycleService(BicycleRepository bicycleRepository, BranchRepository branchRepository) {
        this.bicycleRepository = bicycleRepository;
        this.branchRepository = branchRepository;
    }

    public ResponseEntity<?> save(String bicycleName, double latitude, double longitude) {
        if (bicycleRepository.findByBicycleName(bicycleName).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_BICYCLE");
        }
        Bicycle bicycle = new Bicycle();
        bicycle.setBicycleName(bicycleName);
        bicycle.setLatitude(latitude);
        bicycle.setLongitude(longitude);
        bicycle.setRentalStatus("1");
        bicycle.setBicycleStatus("1");
        bicycle.setReportStatus("1");
        bicycle = bicycleRepository.save(bicycle);
        return ResponseEntity.ok(bicycle);
    }

    public List<Bicycle> getBicyclesByLocation(double latitude, double longitude) {
        return bicycleRepository.findByLocation(latitude, longitude);
    }

    public List<Bicycle> getAllBicycles(String reportStatus) {
        if (reportStatus.equals("notReported")) {
            reportStatus = "1";
        } else {
            reportStatus = "0";
        }
        System.out.println(reportStatus);
        return bicycleRepository.findAllByReportStatus(reportStatus);
    }

    public ResponseEntity<?> updateBicycle(Integer bicycleId, String bicycleName,
        String bicycleStatus, int branchId) {
        boolean exist = bicycleRepository.existsByBicycleNameAndBicycleIdNot(bicycleName,
            bicycleId);
        if (exist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_NAME");
        }
        Bicycle bicycle = bicycleRepository.findByBicycleId(bicycleId);
        Branch branch = branchRepository.findById(branchId).orElse(null);
        bicycle.setBicycleName(bicycleName);
        bicycle.setBicycleStatus(bicycleStatus);
        bicycle.setLatitude(branch.getLatitude());
        bicycle.setLongitude(branch.getLongitude());
        bicycle = bicycleRepository.save(bicycle);
        return ResponseEntity.ok(bicycle);
    }

    public Bicycle getBicycleById(int bicycleId) {
        return bicycleRepository.findByBicycleId(bicycleId);
    }

    public List<Bicycle> findAll() {
        return bicycleRepository.findAll();
    }
}
