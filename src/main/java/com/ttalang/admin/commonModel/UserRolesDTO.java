package com.ttalang.admin.commonModel;


import lombok.Data;

@Data
public class UserRolesDTO {
    private String userName;
    private String customerName;
    private String customerPhone;
    private String birthday;
    private String email;

    public UserRolesDTO(String userName, String customerName, String customerPhone, String birthday,
        String email) {
        this.userName = userName;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.birthday = birthday;
        this.email = email;
    }
}
