package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.entity.CategoryMenuEntity;

import java.util.stream.Collectors;

public abstract class CategoryMenuMapper {

    public static CategoryMenuEntity dtoToEntity(CreateCategoryMenuDto categoryMenuDto) {
        CategoryMenuEntity menuCategory = new CategoryMenuEntity();
        menuCategory.setName(categoryMenuDto.getName());
        menuCategory.setActive(categoryMenuDto.getActive());
        menuCategory.setMenuItems(categoryMenuDto.getMenuItems().stream().map(MenuItemMapper::dtoToEntity).collect(Collectors.toList()));
        return menuCategory;
    };

}
