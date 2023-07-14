package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.stream.Collectors;

public class CompanyMenuMapper {

    public static FindCompanyMenuResponse companyMenuEntityToFindCompanyMenuResponse(CompanyMenuEntity companyMenuEntity){
        FindCompanyMenuResponse findCompanyMenuResponse = new FindCompanyMenuResponse();
        findCompanyMenuResponse.setId(companyMenuEntity.getId());
        findCompanyMenuResponse.setTitle(companyMenuEntity.getTitle());
        findCompanyMenuResponse.setDescription(companyMenuEntity.getDescription());
        findCompanyMenuResponse.setHeader(companyMenuEntity.getHeader());
        findCompanyMenuResponse.setFooter(companyMenuEntity.getFooter());
        findCompanyMenuResponse.setCompanyDataId(companyMenuEntity.getCompanyDataId());
        findCompanyMenuResponse.setActive(companyMenuEntity.getActive());
        findCompanyMenuResponse.setCreatedAt(companyMenuEntity.getCreatedAt());
        findCompanyMenuResponse.setModifiedAt(companyMenuEntity.getModifiedAt());
        findCompanyMenuResponse.setDeletedAt(companyMenuEntity.getDeletedAt());
        findCompanyMenuResponse.setCategories(companyMenuEntity.getCategories().stream().map(CategoryMenuMapper::entityToResponse).collect(Collectors.toList()));
        return findCompanyMenuResponse;
    }

    public static CompanyMenuEntity dtoToEntity(CreateCompanyMenuDto companyMenuDto) {
        CompanyMenuEntity menuEntity = new CompanyMenuEntity();
        menuEntity.setTitle(companyMenuDto.getTitle());
        menuEntity.setDescription(companyMenuDto.getDescription());
        menuEntity.setCompanyDataId(companyMenuDto.getCompanyDataId());
        menuEntity.setHeader(companyMenuDto.getHeader());
        menuEntity.setFooter(companyMenuDto.getFooter());
        menuEntity.setActive(companyMenuDto.getActive());
        menuEntity.setCreatedAt(companyMenuDto.getCreatedAt());
        menuEntity.setModifiedAt(companyMenuDto.getModifiedAt());
        menuEntity.setDeletedAt(companyMenuDto.getDeletedAt());
        return menuEntity;
    }
}
