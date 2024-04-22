package com.project.menuflash.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateCompanyMenuDto {
    private String branch;
    private String title;
    private String description;
    private String header;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
}
