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
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key 자동생성
    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "branch_name")
    private String branchName;

    private double latitude;

    private double longitude;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "branch_status")
    private String branchStatus;

    public Branch(String branchName, double latitude, double longitude, String roadAddress) {
        this.branchName = branchName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roadAddress = roadAddress;
    }
}
