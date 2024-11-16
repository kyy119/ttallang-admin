package com.ttalang.admin.repository;


import com.ttalang.admin.commonModel.Payment;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByPaymentDateBetween(LocalDateTime start, LocalDateTime end);
}
