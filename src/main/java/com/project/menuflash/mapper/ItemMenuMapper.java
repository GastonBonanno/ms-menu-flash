package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import com.project.menuflash.entity.ItemMenuEntity;

public class ItemMenuMapper {

    public static ItemMenuEntity dtoToEntity(CreateItemMenuDto menuItemDto) {
        ItemMenuEntity itemMenuEntity = new ItemMenuEntity();
        itemMenuEntity.setName(menuItemDto.getName());
        itemMenuEntity.setDescription(menuItemDto.getDescription());
        itemMenuEntity.setPrice(menuItemDto.getPrice());
        itemMenuEntity.setActive(menuItemDto.getActive());
        itemMenuEntity.setCreatedAt(menuItemDto.getCreatedAt());
        itemMenuEntity.setModifiedAt(menuItemDto.getModifiedAt());
        itemMenuEntity.setDeletedAt(menuItemDto.getDeletedAt());
        return itemMenuEntity;
    }

    public static FindAllMenuItemResponse entityToResponse(ItemMenuEntity itemMenuEntity){
        FindAllMenuItemResponse itemMenu = new FindAllMenuItemResponse();
        itemMenu.setId(itemMenuEntity.getId());
        itemMenu.setName(itemMenuEntity.getName());
        itemMenu.setDescription(itemMenuEntity.getDescription());
        itemMenu.setPrice(itemMenuEntity.getPrice());
        itemMenu.setActive(itemMenuEntity.getActive());
        itemMenu.setCreatedAt(itemMenuEntity.getCreatedAt());
        itemMenu.setModifiedAt(itemMenuEntity.getModifiedAt());
        itemMenu.setDeletedAt(itemMenuEntity.getDeletedAt());
        return itemMenu;
    }
}
