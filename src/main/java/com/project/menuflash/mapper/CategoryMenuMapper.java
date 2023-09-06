package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.dto.response.CategoryMenuResponse;
import com.project.menuflash.entity.CategoryMenuEntity;

import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryMenuMapper {

    public static CategoryMenuResponse entityToResponse(CategoryMenuEntity categoryMenuEntity){
        CategoryMenuResponse categoryMenu = new CategoryMenuResponse();
        categoryMenu.setId(categoryMenuEntity.getId());
        categoryMenu.setName(categoryMenuEntity.getName());
        categoryMenu.setActive(categoryMenuEntity.getActive());
        categoryMenu.setCompanyMenuId(categoryMenuEntity.getCompanyMenuId());
        categoryMenu.setMenuItems(categoryMenuEntity.getItemsMenu().stream().map(ItemMenuMapper::entityToResponse).collect(Collectors.toList()));
        return categoryMenu;
    }

    public static CategoryMenuEntity dtoToEntity(CreateCategoryMenuDto categoryMenuDto) {
        CategoryMenuEntity menuCategory = new CategoryMenuEntity();
        menuCategory.setName(categoryMenuDto.getName());
        menuCategory.setActive(categoryMenuDto.getActive());
        menuCategory.setCompanyMenuId(categoryMenuDto.getCompanyMenuId());
        return menuCategory;
    }

    public static CategoryMenuEntity updateFromDto(UpdateCategoryMenuDto dto, CategoryMenuEntity categoryMenuEntity) {
        categoryMenuEntity.setName(Optional.ofNullable(dto.getName()).orElse(categoryMenuEntity.getName()));
        categoryMenuEntity.setActive(Optional.ofNullable(dto.getActive()).orElse(categoryMenuEntity.getActive()));
        return categoryMenuEntity;
    }
}
