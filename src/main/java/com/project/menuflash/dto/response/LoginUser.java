package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class LoginUser {
    private String email;
    private String name;
    private String cuit;
    private String address;
    private String phoneNumber;
}


