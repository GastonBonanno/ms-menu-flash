package com.project.menuflash.dto.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class QrResponse {
    private Long id;
    private String tableName;
    private Long companyMenuId;
}
