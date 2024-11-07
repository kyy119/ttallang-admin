package com.ttalang.admin.service;


import com.ttalang.admin.commonModel.Bicycle;
import com.ttalang.admin.repository.BicycleRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    public BicycleService(BicycleRepository bicycleRepository){
        this.bicycleRepository = bicycleRepository;
    }
    public Bicycle save(String bicycleName, double latitude, double longitude){
        if(bicycleRepository.findByBicycleName(bicycleName).isPresent()){
            throw new IllegalArgumentException("EXIST_BICYCLE");
        }
        Bicycle bicycle = new Bicycle();
        bicycle.setBicycleName(bicycleName);
        bicycle.setLatitude(latitude);
        bicycle.setLongitude(longitude);
        bicycle.setRentalStatus("1");
        bicycle.setBicycleStatus("1");
        bicycle.setReportStatus("1");
        return bicycleRepository.save(bicycle);
    }
    public List<Bicycle> getBicyclesByLocation(double latitude, double longitude){
        return bicycleRepository.findByLocation(latitude, longitude);
    }
    public List<Bicycle> getAllBicycles(String reportStatus){
        if(reportStatus.equals("notReported")){
            reportStatus = "1";
        }else {
            reportStatus = "0";
        }
        System.out.println(reportStatus);
        return bicycleRepository.findAllByReportStatus(reportStatus);
    }
    public Bicycle updateBicycle(Integer bicycleId, String bicycleName, String bicycleStatus){
        boolean exist = bicycleRepository.existsByBicycleNameAndBicycleIdNot(bicycleName, bicycleId);
        if(exist){
            throw new DataIntegrityViolationException("EXIST_NAME");
        }
        Bicycle bicycle = bicycleRepository.findByBicycleId(bicycleId);
        bicycle.setBicycleName(bicycleName);
        bicycle.setBicycleStatus(bicycleStatus);
        return bicycleRepository.save(bicycle);
    }
    public Bicycle getBicycleById(int bicycleId){
        return bicycleRepository.findByBicycleId(bicycleId);
    }
    public List<Bicycle> findAll(){
        return bicycleRepository.findAll();
    }
}
