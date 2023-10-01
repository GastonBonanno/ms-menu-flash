package com.project.menuflash.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ItemMenuResponse {
    private Long id;
    private Long categoryMenuId;
    private Long position;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
}
