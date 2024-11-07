package com.ttalang.admin.controller;


import com.ttalang.admin.commonModel.Bicycle;
import com.ttalang.admin.commonModel.Branch;
import com.ttalang.admin.commonModel.FaultReport;
import com.ttalang.admin.commonModel.Payment;
import com.ttalang.admin.commonModel.ReportSummaryDTO;
import com.ttalang.admin.commonModel.UserRolesDTO;
import com.ttalang.admin.service.BicycleService;
import com.ttalang.admin.service.BranchService;
import com.ttalang.admin.service.BranchWithBicycleCount;
import com.ttalang.admin.service.FaultReportService;
import com.ttalang.admin.service.PaymentService;
import com.ttalang.admin.service.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {

    private final BranchService branchService;
    private final BicycleService bicycleService;
    private final FaultReportService faultReportService;
    private final PaymentService paymentService;
    private final UserService userService;

    public AdminRestController(BranchService branchService, BicycleService bicycleService,
        FaultReportService faultReportService, PaymentService paymentService, UserService userService) {
        this.branchService = branchService;
        this.bicycleService = bicycleService;
        this.faultReportService = faultReportService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("/branches")
    public List<BranchWithBicycleCount> getBranchesWithBicycleCounts() {
        return branchService.getBranchesWithBicycleCounts();
    }
    @GetMapping("/branches/{branchId}")
    public Branch getBranchById(@PathVariable int branchId) {
        System.out.println(bicycleService.getBicycleById(branchId));
        return branchService.getBranchById(branchId);
    }
    @PostMapping("/branches/add")
    public String createBranch(@RequestParam String branchName, @RequestParam String streetAdr) {
        try {
            branchService.saveBranch(branchName, streetAdr);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("EXIST_BRANCH")) {
                return "EXIST_BRANCH";
            } else if (e.getMessage().contains("EXIST_ADDRESS")) {
                return "EXIST_ADDRESS";
            }
        }
        return "SUCCESS";
    }
    @PostMapping("/branches/update")
    public String updateBranch(@RequestParam Integer branchId, @RequestParam String branchName, @RequestParam String streetAdr, @RequestParam String branchStatus){
        try {
            System.out.println(branchService.updateBranch(branchId, branchName, streetAdr, branchStatus));
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            return e.getMessage();
        }
    }
    @PostMapping("/bicycle/add")
    public String bicycleAdd(@RequestParam String bicycleName, @RequestParam double latitude, @RequestParam double longitude){
        System.out.println(bicycleName + latitude + longitude);
        try{
            bicycleService.save(bicycleName, latitude, longitude);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
        return "SUCCESS";
    }
    @GetMapping("/bicycle/byLocation")
    public List<Bicycle> byLocation(@RequestParam double latitude, @RequestParam double longitude){
        return bicycleService.getBicyclesByLocation(latitude, longitude);
    }
    @GetMapping("/bicycle/allBicycle")
    public List<Bicycle> getAllBicycle(){
        return bicycleService.findAll();
    }
    @GetMapping("/bicycle/report")
    public List<Bicycle> getBicycles(@RequestParam String reportStatus) {
        System.out.println(reportStatus);
        System.out.println(bicycleService.getAllBicycles(reportStatus));
        return bicycleService.getAllBicycles(reportStatus);
    }
    @GetMapping("/bicycle/{bicycleId}")
    public Bicycle getBicycleById(@PathVariable int bicycleId) {
        return bicycleService.getBicycleById(bicycleId);
    }
    @PostMapping("/bicycle/update")
    public String updateBicycle(@RequestParam Integer bicycleId, @RequestParam String bicycleName, @RequestParam String bicycleStatus){
        System.out.println( "bicycleStatus : "+bicycleStatus);
        try {
            bicycleService.updateBicycle(bicycleId, bicycleName, bicycleStatus);
            return "SUCCESS";
        }catch (DataIntegrityViolationException e){
            return e.getMessage();
        }
    }
    @PostMapping("/bicycle/report/update")
    public FaultReport reportUpdate(@RequestParam Integer bicycleId, @RequestParam Integer reportId){
        return faultReportService.updateReport(bicycleId,reportId);
    }
    @GetMapping("/bicycle/reported/{bicycleId}")
    public ResponseEntity<ReportSummaryDTO> getReportedBicycleById(@PathVariable int bicycleId){
        ReportSummaryDTO response = faultReportService.findReportedBicycles(bicycleId);
        System.out.println(response);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(faultReportService.findReportedBicycles(bicycleId));
    }
    @GetMapping("/payment")
    public List<Payment> findPayment(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        System.out.println(start + " : "+ end);
        System.out.println(paymentService.findByPaymentDateBetween(start.atStartOfDay(), end.atTime(23, 59)));
        return paymentService.findByPaymentDateBetween(start.atStartOfDay(), end.atTime(23, 59));
    }
    @GetMapping("/member/allMemberList")
    public List<UserRolesDTO> allMemberList(){
        System.out.println(userService.findAllUserDetails());
        return userService.findAllUserDetails();
    }
    @GetMapping("/member/noPayList")
    public List<Object[]> noPayList(){
        System.out.println(userService.findUnpaidPayments());
        return userService.findUnpaidPayments();
    }
}