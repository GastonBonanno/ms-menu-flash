package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.entity.CategoryMenuEntity;

public abstract class CategoryMenuMapper {

    public static CategoryMenuEntity dtoToEntity(CreateCategoryMenuDto categoryMenuDto) {
        CategoryMenuEntity menuCategory = new CategoryMenuEntity();
        menuCategory.setId(menuCategory.getId());
        menuCategory.setName(menuCategory.getName());
        menuCategory.setActive(menuCategory.getActive());
        menuCategory.setMenuItems(menuCategory.getMenuItems());
        return menuCategory;
    };

}
