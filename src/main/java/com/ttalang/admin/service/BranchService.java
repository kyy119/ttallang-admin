package com.ttalang.admin.service;


import com.ttalang.admin.commonModel.Bicycle;
import com.ttalang.admin.commonModel.BranchWithBicycleCount;
import com.ttalang.admin.kakao.KakaoApiResponse;
import com.ttalang.admin.commonModel.Branch;
import com.ttalang.admin.repository.BicycleRepository;
import com.ttalang.admin.repository.BranchRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public ResponseEntity<?> updateBranch(Integer branchId, String branchName, String streetAdr,
        String branchStatus) {
        boolean exists = branchRepository.existsByBranchNameAndBranchIdNot(branchName, branchId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_BRANCH");
        }
        exists = branchRepository.existsByRoadAddressAndBranchIdNot(streetAdr, branchId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_STREET");
        }
        Branch branch = branchRepository.findById(branchId)
            .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + streetAdr;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET,
            entity, KakaoApiResponse.class);

        double latitude = response.getBody().getDocuments().get(0).getY();
        double longitude = response.getBody().getDocuments().get(0).getX();

        List<Bicycle> bicycles = bicycleRepository.findAllByLatitudeAndLongitude(
            branch.getLatitude(), branch.getLongitude());
        for (int i = 0; i < bicycles.size(); i++) {
            bicycles.get(i).setBicycleStatus(branchStatus);
            bicycles.get(i).setLatitude(latitude);
            bicycles.get(i).setLongitude(longitude);
            bicycleRepository.save(bicycles.get(i));
        }
        branch.setBranchName(branchName);
        branch.setRoadAddress(streetAdr);
        branch.setLatitude(latitude);
        branch.setLongitude(longitude);
        branch.setBranchStatus(branchStatus);
        Branch updatedBranch = branchRepository.save(branch);
        return ResponseEntity.ok(updatedBranch);
    }

    public ResponseEntity<?> saveBranch(String branchName, String streetAdr) {
        if (branchRepository.findByBranchName(branchName).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_BRANCH");
        }
        if (branchRepository.findByRoadAddress(streetAdr).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EXIST_STREET");
        }
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + streetAdr;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET,
            entity, KakaoApiResponse.class);

        double latitude = response.getBody().getDocuments().get(0).getY();
        double longitude = response.getBody().getDocuments().get(0).getX();

        Branch branch = new Branch(branchName, latitude, longitude, streetAdr);
        branch.setBranchStatus("1");
        Branch saveBranch = branchRepository.save(branch);
        return ResponseEntity.ok(saveBranch);
    }

    public List<BranchWithBicycleCount> getBranchesWithBicycleCounts() {
        return branchRepository.findAll().stream().map(branch -> {
            int bicycleCount = bicycleRepository.countByLocation(branch.getLatitude(),
                branch.getLongitude());
            return new BranchWithBicycleCount(branch.getBranchId(), branch.getBranchStatus(),
                branch.getBranchName(), bicycleCount);
        }).collect(Collectors.toList());
    }


}
