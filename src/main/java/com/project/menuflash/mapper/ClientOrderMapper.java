package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindAllClientOrderItemResponse;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.Optional;
import java.util.stream.Collectors;

public class ClientOrderMapper {

    public static FindAllClientOrderResponse entityToResponse(ClientOrderEntity clientOrderEntity){
        FindAllClientOrderResponse clientOrderResponse = new FindAllClientOrderResponse();
        clientOrderResponse.setId(clientOrderEntity.getId());
        clientOrderResponse.setOrderId(clientOrderEntity.getOrderId());
        clientOrderResponse.setState(StateMapper.entityToResponse(clientOrderEntity.getStateEntity()));
        clientOrderResponse.setTableName(clientOrderEntity.getTableName());
        clientOrderResponse.setCompanyMenuId(clientOrderEntity.getCompanyMenuId());
        clientOrderResponse.setActive(clientOrderEntity.getActive());
        clientOrderResponse.setCreatedAt(clientOrderEntity.getCreatedAt());
        clientOrderResponse.setModifiedAt(clientOrderEntity.getModifiedAt());
        clientOrderResponse.setDeletedAt(clientOrderEntity.getDeletedAt());
        clientOrderResponse.setClientOrderItemList(clientOrderEntity.getClientOrderItemEntityList()
                                                            .stream()
                                                            .map(ClientOrderItemMapper::entityToResponse)
                                                            .collect(Collectors.toList()));
        return clientOrderResponse;
    }
}
