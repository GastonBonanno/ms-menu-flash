package com.project.menuflash.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CompanyDataResponse {
    private String name;
    private Long cuit;
    private String address;
    private Long phoneNumber;
}
