package com.ttalang.admin.security.repository;


import com.ttalang.admin.commonModel.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles findByUserName(String userName);
}
