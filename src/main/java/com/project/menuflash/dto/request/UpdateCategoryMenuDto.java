package com.project.menuflash.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UpdateCategoryMenuDto {
    private String name;
    private Long position;
    private Boolean active;
}
