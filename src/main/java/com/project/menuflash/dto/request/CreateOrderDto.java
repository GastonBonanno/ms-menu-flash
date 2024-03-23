package com.project.menuflash.dto.request;

import com.project.menuflash.entity.ClientOrderItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private Long orderId;
    private String tableName;
    private String clientEmail;
    private Long companyMenuId;
    private List<ClientOrderItemDto> clientOrderItemDto;
}
