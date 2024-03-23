package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.ClientOrderItemDto;
import com.project.menuflash.dto.response.ClientOrderItemResponse;
import com.project.menuflash.dto.response.FindAllClientOrderItemResponse;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ClientOrderItemEntity;

public class ClientOrderItemMapper {

    public static FindAllClientOrderItemResponse entityToResponse(ClientOrderItemEntity clientOrderItemEntity) {
        FindAllClientOrderItemResponse clientOrderItemResponse = new FindAllClientOrderItemResponse();
        clientOrderItemResponse.setId(clientOrderItemEntity.getId());
        clientOrderItemResponse.setAdditionalComments(clientOrderItemEntity.getAdditionalComments());
        clientOrderItemResponse.setItemMenuId(clientOrderItemEntity.getItemMenuId());
        clientOrderItemResponse.setClientOrderId(clientOrderItemEntity.getClientOrderId());
        clientOrderItemResponse.setItemName(clientOrderItemEntity.getItemName());
        clientOrderItemResponse.setDescription(clientOrderItemEntity.getDescription());
        clientOrderItemResponse.setQuantity(clientOrderItemEntity.getQuantity());
        return clientOrderItemResponse;
    }

    public static ClientOrderItemEntity createOrderItemEntityToResponse(ClientOrderItemDto clientOrderItemDto){
        ClientOrderItemEntity dataResponse = new ClientOrderItemEntity();
        dataResponse.setAdditionalComments(clientOrderItemDto.getAdditionalComments());
        dataResponse.setItemMenuId(clientOrderItemDto.getItemMenuId());
        dataResponse.setItemName(clientOrderItemDto.getItemName());
        dataResponse.setDescription(clientOrderItemDto.getDescription());
        dataResponse.setQuantity(clientOrderItemDto.getQuantity());
        return dataResponse;
    }
}
