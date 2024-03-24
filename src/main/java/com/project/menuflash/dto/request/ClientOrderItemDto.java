package com.project.menuflash.dto.request;

import lombok.Data;

@Data
public class ClientOrderItemDto {
    private String additionalComments;
    private Long itemMenuId;
    private String itemName;
    private String description;
    private Long quantity;
}