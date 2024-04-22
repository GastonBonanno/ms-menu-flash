package com.project.menuflash.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateItemMenuDto {
    private Long categoryMenuId;
    private String name;
    private Long position;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
}
