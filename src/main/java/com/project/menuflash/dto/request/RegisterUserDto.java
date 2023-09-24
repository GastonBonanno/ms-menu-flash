package com.project.menuflash.dto.request;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String companyName;
    private Long cuit;
    private String address;
    private Long phoneNumber;
}
