package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class QrResponse {
    private Long id;
    private Long companyId;
    private String tableName;
    private Long companyMenuId;
}
