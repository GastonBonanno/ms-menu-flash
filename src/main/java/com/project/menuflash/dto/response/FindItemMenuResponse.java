package com.project.menuflash.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FindItemMenuResponse {
    private Long categoryMenuId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
}
