package com.project.menuflash.dto.request;

import lombok.Data;

@Data
public class CreateQrDto {
    private String tableName;
    private Long companyMenuId;
}
