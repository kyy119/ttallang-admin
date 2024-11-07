package com.ttalang.admin.security.config.auth;

import com.ttalang.admin.commonModel.Roles;
import com.ttalang.admin.security.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 .loginProcessingUrl("/login") 요청이 오면
//UserDetailsService 타입으로 Ioc 되어 있는 loadUserByUsername 함수 실행 => 자동
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final RolesRepository rolesRepository;

    public PrincipalDetailsService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    //시큐리티 session(내부 Authentication(내부 UserDetails)) 들어감
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // userName 은 Roles 테이블에 등록되있는 유저의 아이디 (Unique 해야함.)
        log.info("userName={}", userName);
        Roles roles = rolesRepository.findByUserName(userName);
        return new PrincipalDetails(roles);
    }
}
