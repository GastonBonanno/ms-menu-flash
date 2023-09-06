package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import com.project.menuflash.entity.ItemMenuEntity;

import java.util.Optional;

public class ItemMenuMapper {

    public static FindAllMenuItemResponse entityToResponse(ItemMenuEntity itemMenuEntity){
        FindAllMenuItemResponse itemMenu = new FindAllMenuItemResponse();
        itemMenu.setId(itemMenuEntity.getId());
        itemMenu.setCategoryMenuId(itemMenuEntity.getCategoryMenuId());
        itemMenu.setName(itemMenuEntity.getName());
        itemMenu.setDescription(itemMenuEntity.getDescription());
        itemMenu.setPrice(itemMenuEntity.getPrice());
        itemMenu.setQuantity(itemMenuEntity.getQuantity());
        itemMenu.setActive(itemMenuEntity.getActive());
        itemMenu.setCreatedAt(itemMenuEntity.getCreatedAt());
        itemMenu.setModifiedAt(itemMenuEntity.getModifiedAt());
        itemMenu.setDeletedAt(itemMenuEntity.getDeletedAt());
        return itemMenu;
    }

    public static ItemMenuEntity dtoToEntity(CreateItemMenuDto menuItemDto) {
        ItemMenuEntity itemMenuEntity = new ItemMenuEntity();
        itemMenuEntity.setCategoryMenuId(menuItemDto.getCategoryMenuId());
        itemMenuEntity.setName(menuItemDto.getName());
        itemMenuEntity.setDescription(menuItemDto.getDescription());
        itemMenuEntity.setPrice(menuItemDto.getPrice());
        itemMenuEntity.setQuantity(menuItemDto.getQuantity());
        itemMenuEntity.setActive(menuItemDto.getActive());
        itemMenuEntity.setCreatedAt(menuItemDto.getCreatedAt());
        itemMenuEntity.setModifiedAt(menuItemDto.getModifiedAt());
        itemMenuEntity.setDeletedAt(menuItemDto.getDeletedAt());
        return itemMenuEntity;
    }

    public static ItemMenuEntity updateFromDto(UpdateItemMenuDto dto,ItemMenuEntity itemMenuEntity) {
        itemMenuEntity.setName(Optional.ofNullable(dto.getName()).orElse(itemMenuEntity.getName()));
        itemMenuEntity.setDescription(Optional.ofNullable(dto.getDescription()).orElse(itemMenuEntity.getDescription()));
        itemMenuEntity.setPrice(Optional.ofNullable(dto.getPrice()).orElse(itemMenuEntity.getPrice()));
        itemMenuEntity.setActive(Optional.ofNullable(dto.getActive()).orElse(itemMenuEntity.getActive()));
        itemMenuEntity.setCreatedAt(Optional.ofNullable(dto.getCreatedAt()).orElse(itemMenuEntity.getCreatedAt()));
        itemMenuEntity.setModifiedAt(Optional.ofNullable(dto.getModifiedAt()).orElse(itemMenuEntity.getModifiedAt()));
        itemMenuEntity.setDeletedAt(Optional.ofNullable(dto.getDeletedAt()).orElse(itemMenuEntity.getDeletedAt()));
        return itemMenuEntity;
    }
}
