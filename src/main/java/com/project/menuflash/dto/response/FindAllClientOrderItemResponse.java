package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class FindAllClientOrderItemResponse {
    private Long id;
    private String additionalComments;
    private Long itemMenuId;
    private Long clientOrderId;
    private String itemName;
    private String description;
    private Long quantity;
}
