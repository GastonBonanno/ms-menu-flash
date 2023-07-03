package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.stream.Collectors;

public abstract class CompanyMenuMapper {

    public CompanyMenuEntity companyMenuDtoToEntity(CreateCompanyMenuDto companyMenuDto) {
        CompanyMenuEntity menuEntity = new CompanyMenuEntity();
        menuEntity.setId(companyMenuDto.getId());
        menuEntity.setTitle(companyMenuDto.getTitle());
        menuEntity.setDescription(companyMenuDto.getDescription());
        menuEntity.setHeader(companyMenuDto.getHeader());
        menuEntity.setFooter(companyMenuDto.getFooter());
        menuEntity.setClientUserId(companyMenuDto.getClientUserId());
        menuEntity.setActive(companyMenuDto.getActive());
        menuEntity.setCreatedAt(companyMenuDto.getCreatedAt());
        menuEntity.setModifiedAt(companyMenuDto.getModifiedAt());
        menuEntity.setDeletedAt(companyMenuDto.getDeletedAt());
        menuEntity.setCategories(companyMenuDto.getCategories().stream().map(CategoryMenuMapper::dtoToEntity).collect(Collectors.toList()));
        return menuEntity;
    };

}
