package com.ttalang.admin.security.service;

import com.ttalang.admin.commonModel.Roles;
import com.ttalang.admin.security.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Service
public class SignupServiceImpl implements SignupService {

    // 의존성 주입.
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 암호화해주는놈.
    private final RolesRepository rolesRepository;

    public SignupServiceImpl(
        BCryptPasswordEncoder bCryptPasswordEncoder,
        RolesRepository rolesRepository
    ) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolesRepository = rolesRepository;
    }

    // 관리자 아이디 존재 여부 확인.
    @Override
    public boolean isExistingId(String userName) {
        Roles roles = rolesRepository.findByUserName(userName);
        return roles != null;
    }

    // 관리자 가입.
    @Override
    public void signupAdmin(Map<String, String> userData) {
        Roles roles = new Roles();

        String userName = userData.get("userName");
        roles.setUserName(userName);

        roles.setUserRole("ROLE_ADMIN");

        String rawPassword = userData.get("userPassword");
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        roles.setUserPassword(encPassword);

        roles.setUserStatus("1");

        rolesRepository.save(roles);
    }
}
