package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class LoggedUser {
    private Long companyId;
    private String email;
    private String name;
    private String cuit;
    private String address;
    private String phoneNumber;
}


