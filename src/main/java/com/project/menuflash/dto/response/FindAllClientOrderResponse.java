package com.project.menuflash.dto.response;

import lombok.Data;

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
