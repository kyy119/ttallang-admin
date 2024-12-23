package com.ttalang.admin.repository;

import com.ttalang.admin.commonModel.Branch;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Optional<Branch> findByRoadAddress(String roadAddress);

    Optional<Branch> findByBranchName(String branchName);

    boolean existsByBranchNameAndBranchIdNot(String branchName, Integer branchId);

    boolean existsByRoadAddressAndBranchIdNot(String streetAdr, Integer branchId);


    @Query("SELECT b.branchName FROM Branch b")
    List<String> findAllBranchNames();

    @Query("SELECT b.branchName FROM Branch b WHERE b.branchId <> :branchId")
    List<String> findBranchNamesExcludingId(Integer branchId);
}
