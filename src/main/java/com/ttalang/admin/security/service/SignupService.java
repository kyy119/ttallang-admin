package com.ttalang.admin.security.service;


import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SignupService {
    // 관리자 회원가입.
    boolean isExistingId(String userName);
    void signupAdmin(Map<String, String> userData);
}
