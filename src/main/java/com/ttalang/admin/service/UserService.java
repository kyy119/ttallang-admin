package com.ttalang.admin.service;

import com.ttalang.admin.commonModel.UserRolesDTO;
import com.ttalang.admin.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<UserRolesDTO> findAllUserDetails(){
        return userRepository.findAllUserDetails();
    }
    public List<Object[]> findUnpaidPayments(){
        return userRepository.findUnpaidPayments();
    }
}
