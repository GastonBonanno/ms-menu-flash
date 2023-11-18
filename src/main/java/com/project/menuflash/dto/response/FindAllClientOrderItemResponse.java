package com.project.menuflash.dto.response;

import com.project.menuflash.entity.ClientOrderItemEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

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
