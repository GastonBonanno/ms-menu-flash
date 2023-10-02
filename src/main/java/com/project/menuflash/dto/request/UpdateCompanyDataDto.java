package com.project.menuflash.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateCompanyDataDto {
    private String name;
    private Long cuit;
    private String address;
    private Long phoneNumber;
}
