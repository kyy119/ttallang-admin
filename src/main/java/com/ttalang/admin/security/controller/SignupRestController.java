package com.ttalang.admin.security.controller;


import com.ttalang.admin.security.response.SecurityResponse;
import com.ttalang.admin.security.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SignupRestController {

    private final SignupService signupService;

    public SignupRestController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/api/signup")
    public SecurityResponse signupAdmin(@RequestBody Map<String, String> userData) {
        SecurityResponse securityResponse = new SecurityResponse();
        try {
            signupService.signupAdmin(userData);
            securityResponse.setCode(200);
            securityResponse.setStatus("success");
            securityResponse.setRole("guest");
            securityResponse.setMessage("회원가입 성공.");
        } catch (Exception e) {
            securityResponse.setCode(500);
            securityResponse.setStatus("failure");
            securityResponse.setRole("guest");
            securityResponse.setMessage("회원가입 실패," + e.getMessage());
            System.out.println("예외 발생: " + e.getMessage());
        }
        return securityResponse;
    }
}
