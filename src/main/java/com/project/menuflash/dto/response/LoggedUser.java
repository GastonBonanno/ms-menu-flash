package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class LoggedUser {
    private Long companyId;
    private String email;
    private String name;
    private Long cuit;
    private String address;
    private Long phoneNumber;
}


