package com.ttalang.admin.repository;

import com.ttalang.admin.commonModel.Bicycle;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Integer> {

    @Query("SELECT COUNT(b) FROM Bicycle b WHERE b.latitude = :latitude AND b.longitude = :longitude")
    int countByLocation(@Param("latitude") double latitude, @Param("longitude") double longitude);

    @Query("SELECT b FROM Bicycle b WHERE b.latitude = :latitude AND b.longitude = :longitude")
    List<Bicycle> findByLocation(@Param("latitude") double latitude,
        @Param("longitude") double longitude);

    List<Bicycle> findAllByReportStatus(String status);

    Bicycle findByBicycleId(int bicycleId);

    Optional<Bicycle> findByBicycleName(String bicycleName);

    boolean existsByBicycleNameAndBicycleIdNot(String bicycleName, int bicycleId);

    List<Bicycle> findAllByLatitudeAndLongitude(double latitude, double longitude);

}
