package com.project.menuflash.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.menuflash.entity.ClientOrderItemEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class FindAllClientOrderResponse {
    private Long id;
    private Long orderId;
    private FindAllStateResponse state;
    private String tableName;
    private String clientEmail;
    private Long companyMenuId;
    private Boolean active;
    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
    private List<FindAllClientOrderItemResponse> clientOrderItemList;
}
