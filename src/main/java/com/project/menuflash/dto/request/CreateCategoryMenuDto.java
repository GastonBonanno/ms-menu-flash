package com.project.menuflash.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateCategoryMenuDto {
    private String name;
    private Long position;
    private Boolean active;
    private Long companyMenuId;
}
