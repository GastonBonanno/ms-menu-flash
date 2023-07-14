package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.response.FindCategoryMenuResponse;
import com.project.menuflash.entity.CategoryMenuEntity;

import java.util.stream.Collectors;

public class CategoryMenuMapper {

    public static FindCategoryMenuResponse entityToResponse(CategoryMenuEntity categoryMenuEntity){
        FindCategoryMenuResponse categoryMenu = new FindCategoryMenuResponse();
        categoryMenu.setId(categoryMenuEntity.getId());
        categoryMenu.setName(categoryMenuEntity.getName());
        categoryMenu.setActive(categoryMenuEntity.getActive());
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
}
