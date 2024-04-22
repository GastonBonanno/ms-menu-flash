package com.project.menuflash.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateCompanyMenuDto {
    private String branch;
    private String title;
    private String description;
    private String header;
    private Long companyDataId;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
}

