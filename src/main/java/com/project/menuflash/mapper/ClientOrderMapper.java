package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateOrderDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ClientOrderItemEntity;

import java.util.stream.Collectors;

public class ClientOrderMapper {

    public static FindAllClientOrderResponse entityToResponse(ClientOrderEntity clientOrderEntity){
        FindAllClientOrderResponse clientOrderResponse = new FindAllClientOrderResponse();
        clientOrderResponse.setId(clientOrderEntity.getId());
        clientOrderResponse.setOrderId(clientOrderEntity.getOrderId());
        clientOrderResponse.setState(StateMapper.entityToResponse(clientOrderEntity.getStateEntity()));
        clientOrderResponse.setTableName(clientOrderEntity.getTableName());
        clientOrderResponse.setClientEmail(clientOrderEntity.getClientEmail());
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

    public static ClientOrderEntity dtoToEntity(CreateOrderDto dto){
        ClientOrderEntity clientOrderEntity = new ClientOrderEntity();
        clientOrderEntity.setOrderId(dto.getOrderId());
        clientOrderEntity.setTableName(dto.getTableName());
        clientOrderEntity.setClientEmail(dto.getClientEmail());
        clientOrderEntity.setCompanyMenuId(dto.getCompanyMenuId());
        clientOrderEntity.setClientOrderItemEntityList(dto.getClientOrderItemDto().stream().map(ClientOrderItemMapper::createOrderItemEntityToResponse).collect(Collectors.toList()));
        return clientOrderEntity;
    }

}
