package com.ttalang.admin.repository;

import com.ttalang.admin.commonModel.User;
import com.ttalang.admin.commonModel.UserRolesDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT new com.ttalang.admin.commonModel.UserRolesDTO(r.userName, u.customerName, u.customerPhone, u.birthday, u.email) " +
        "FROM User u JOIN Roles r ON u.userId = r.userId")
    List<UserRolesDTO> findAllUserDetails();
    @Query("SELECT r.userName,u.customerName ,p.paymentAmount " +
        "FROM Payment p " +
        "JOIN User u ON p.customerId = u.customerId " +
        "JOIN Roles r ON u.userId = r.userId " +
        "WHERE p.paymentStatus = '0'")
    List<Object[]> findUnpaidPayments();
}