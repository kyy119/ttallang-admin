package com.ttalang.admin.service;


import com.ttalang.admin.kakao.KakaoApiResponse;
import com.ttalang.admin.commonModel.Branch;
import com.ttalang.admin.repository.BicycleRepository;
import com.ttalang.admin.repository.BranchRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final BicycleRepository bicycleRepository;
//    private final String KAKAO_API_KEY = "dfd8dd36973eaa0d87e791379b3c28f2";
    @Value("${KAKAO_API_KEY}")
    private String KAKAO_API_KEY;

    public BranchService(BranchRepository branchRepository, BicycleRepository bicycleRepository) {
        this.branchRepository = branchRepository;
        this.bicycleRepository = bicycleRepository;
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
    public Branch getBranchById(int branchId) {
        return branchRepository.findById(branchId).orElse(null);
    }
    public Branch updateBranch(Integer branchId, String branchName, String streetAdr, String branchStatus){
        boolean exists = branchRepository.existsByBranchNameAndBranchIdNot(branchName, branchId);
        if (exists) {
            throw new DataIntegrityViolationException("EXIST_NAME");
        }
        exists = branchRepository.existsByRoadAddressAndBranchIdNot(streetAdr, branchId);
        if(exists){
            throw new DataIntegrityViolationException("EXIST_STREET");
        }
        Branch branch = branchRepository.findById(branchId)
            .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + streetAdr;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, KakaoApiResponse.class);

        double latitude = response.getBody().getDocuments().get(0).getY();
        double longitude = response.getBody().getDocuments().get(0).getX();

        branch.setBranchName(branchName);
        branch.setRoadAddress(streetAdr);
        branch.setLatitude(latitude);
        branch.setLongitude(longitude);
        branch.setBranchStatus(branchStatus);
        return branchRepository.save(branch);
    }
    public Branch saveBranch(String branchName, String streetAdr) {
        if (branchRepository.findByRoadAddress(streetAdr).isPresent()) {
            throw new IllegalArgumentException("EXIST_ADDRESS");
        }
        if (branchRepository.findByBranchName(branchName).isPresent()) {
            throw new IllegalArgumentException("EXIST_BRANCH");
        }
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + streetAdr;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, KakaoApiResponse.class);

        double latitude = response.getBody().getDocuments().get(0).getY();
        double longitude = response.getBody().getDocuments().get(0).getX();

        Branch branch = new Branch(branchName, latitude, longitude, streetAdr);
        branch.setBranchStatus("1");
        return branchRepository.save(branch);
    }
    public List<BranchWithBicycleCount> getBranchesWithBicycleCounts() {
        return branchRepository.findAll().stream().map(branch -> {
            int bicycleCount = bicycleRepository.countByLocation(branch.getLatitude(), branch.getLongitude());
            return new BranchWithBicycleCount(branch.getBranchId(),branch.getBranchStatus(),branch.getBranchName(), bicycleCount);
        }).collect(Collectors.toList());
    }


}
