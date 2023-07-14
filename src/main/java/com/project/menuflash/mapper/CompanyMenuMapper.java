package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindCategoryMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CategoryMenuEntity;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.stream.Collectors;

public class CompanyMenuMapper {

    public static CompanyMenuEntity dtoToEntity(CreateCompanyMenuDto companyMenuDto) {
        CompanyMenuEntity menuEntity = new CompanyMenuEntity();
//        menuEntity.setId(companyMenuDto.getId()); // falta agregar en CreateCompanyMenuDto ?? CONSULTAR!!
        menuEntity.setTitle(companyMenuDto.getTitle());
        menuEntity.setDescription(companyMenuDto.getDescription());
        menuEntity.setClientUserId(companyMenuDto.getClientUserId());
        menuEntity.setHeader(companyMenuDto.getHeader());
        menuEntity.setFooter(companyMenuDto.getFooter());
        menuEntity.setActive(companyMenuDto.getActive());
        menuEntity.setCreatedAt(companyMenuDto.getCreatedAt());
        menuEntity.setModifiedAt(companyMenuDto.getModifiedAt());
        menuEntity.setDeletedAt(companyMenuDto.getDeletedAt());
        return menuEntity;
    };

    public static FindCompanyMenuResponse entityToResponse(CompanyMenuEntity companyMenuEntity){
        FindCompanyMenuResponse companyMenu = new FindCompanyMenuResponse();
        companyMenu.setId(companyMenuEntity.getId());
        companyMenu.setTitle(companyMenuEntity.getTitle());
        companyMenu.setDescription(companyMenuEntity.getDescription());
        companyMenu.setHeader(companyMenuEntity.getHeader());
        companyMenu.setFooter(companyMenuEntity.getFooter());
        companyMenu.setActive(companyMenuEntity.getActive());
        companyMenu.setCreatedAt(companyMenuEntity.getCreatedAt());
        companyMenu.setModifiedAt(companyMenuEntity.getModifiedAt());
        companyMenu.setDeletedAt(companyMenuEntity.getDeletedAt());
        companyMenu.setCategories(companyMenuEntity.getCategories().stream().map(CategoryMenuMapper::entityToResponse).collect(Collectors.toList()));
        return companyMenu;
    }

    public static FindCategoryMenuResponse mapperToResponse(CategoryMenuEntity categoryMenuEntity){
        FindCategoryMenuResponse findCategoryMenuResponse = new FindCategoryMenuResponse();
        findCategoryMenuResponse.setId(categoryMenuEntity.getId());
        findCategoryMenuResponse.setName(categoryMenuEntity.getName());
        findCategoryMenuResponse.setActive(categoryMenuEntity.getActive());
        return findCategoryMenuResponse;
    }
}
