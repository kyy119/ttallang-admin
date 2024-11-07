package com.ttalang.admin.commonModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bicycle")
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key 자동생성
    @Column(name = "bicycle_id")
    private int bicycleId;

    @Column(name = "bicycle_name")
    private String bicycleName;

    @Column(name = "bicycle_status")
    private String bicycleStatus;

    @Column(name = "rental_status")
    private String rentalStatus;

    @Column(name = "report_status")
    private String reportStatus;

    private double latitude;

    private double longitude;


}
