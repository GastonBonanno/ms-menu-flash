package com.project.menuflash.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FindCompanyMenuResponse {
    private Long id;
    private String branch;
    private String title;
    private String description;
    private String header;
    private Long companyDataId;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
    private List<CategoryMenuResponse> listCategory;
}

