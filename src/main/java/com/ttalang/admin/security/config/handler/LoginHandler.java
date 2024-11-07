package com.ttalang.admin.security.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttalang.admin.security.response.SecurityResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            response.setContentType("application/json;charset=UTF-8");
            SecurityResponse securityResponse = new SecurityResponse(200, "success","admin", "관리자 로그인 성공.");

            new ObjectMapper().writeValue(response.getWriter(), securityResponse);
        } else { // 관리자 로그인이 되었지만 올바른 관리자 권한(ROLE_ADMIN)을 찾을 수 없으므로 401 처리.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            SecurityResponse securityResponse = new SecurityResponse(401, "failure", "unknown", "권한 정보가 잘못되었습니다.");

            new ObjectMapper().writeValue(response.getWriter(), securityResponse);
        }
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.setCode(401);
        securityResponse.setStatus("failure");
        securityResponse.setRole("guest");
        if (exception.getMessage().contains("Bad credentials")) {
            securityResponse.setMessage("관리자 비밀번호를 확인해주세요.");
        } else if (exception.getMessage().contains("User account is disabled")) {
            securityResponse.setMessage("비활성화된 관리자입니다.");
        } else {
            securityResponse.setMessage("관리자 로그인에 실패하였습니다.");
        }

        new ObjectMapper().writeValue(response.getWriter(), securityResponse);
    }
}