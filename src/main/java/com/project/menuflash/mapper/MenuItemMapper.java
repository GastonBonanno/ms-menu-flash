package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateMenuItemDto;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.entity.MenuItemEntity;

import java.util.stream.Collectors;

public abstract class MenuItemMapper {

    public static MenuItemEntity dtoToEntity(CreateMenuItemDto menuItemDto) {
        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setActive(menuItemDto.getActive());
        menuItem.setCreatedAt(menuItemDto.getCreatedAt());
        menuItem.setModifiedAt(menuItemDto.getModifiedAt());
        menuItem.setDeletedAt(menuItemDto.getDeletedAt());
        return menuItem;
    };

}
