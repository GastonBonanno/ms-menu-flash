package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyMenuMapper {

    public static FindCompanyMenuResponse companyMenuEntityToFindCompanyMenuResponse(CompanyMenuEntity companyMenuEntity){
        FindCompanyMenuResponse findCompanyMenuResponse = new FindCompanyMenuResponse();
        findCompanyMenuResponse.setId(companyMenuEntity.getId());
        findCompanyMenuResponse.setBranch(companyMenuEntity.getBranch());
        findCompanyMenuResponse.setTitle(companyMenuEntity.getTitle());
        findCompanyMenuResponse.setDescription(companyMenuEntity.getDescription());
        findCompanyMenuResponse.setHeader(companyMenuEntity.getHeader());
        findCompanyMenuResponse.setCompanyDataId(companyMenuEntity.getCompanyDataId());
        findCompanyMenuResponse.setActive(companyMenuEntity.getActive());
        findCompanyMenuResponse.setCreatedAt(companyMenuEntity.getCreatedAt());
        findCompanyMenuResponse.setModifiedAt(companyMenuEntity.getModifiedAt());
        findCompanyMenuResponse.setDeletedAt(companyMenuEntity.getDeletedAt());
        findCompanyMenuResponse.setListCategory(companyMenuEntity.getCategories().stream().map(CategoryMenuMapper::entityToResponse).collect(Collectors.toList()));
        return findCompanyMenuResponse;
    }
    public static CreateCompanyMenuResponse entityToResponse(CompanyMenuEntity companyMenuEntity){
        CreateCompanyMenuResponse createCompanyMenuResponse = new CreateCompanyMenuResponse();
        createCompanyMenuResponse.setId(companyMenuEntity.getId());
        createCompanyMenuResponse.setBranch(companyMenuEntity.getBranch());
        createCompanyMenuResponse.setTitle(companyMenuEntity.getTitle());
        createCompanyMenuResponse.setDescription(companyMenuEntity.getDescription());
        createCompanyMenuResponse.setHeader(companyMenuEntity.getHeader());
        createCompanyMenuResponse.setCompanyDataId(companyMenuEntity.getCompanyDataId());
        createCompanyMenuResponse.setActive(companyMenuEntity.getActive());
        createCompanyMenuResponse.setCreatedAt(companyMenuEntity.getCreatedAt());
        createCompanyMenuResponse.setModifiedAt(companyMenuEntity.getModifiedAt());
        createCompanyMenuResponse.setDeletedAt(companyMenuEntity.getDeletedAt());
        return createCompanyMenuResponse;
    }


    public static CompanyMenuEntity dtoToEntity(CreateCompanyMenuDto companyMenuDto) {
        CompanyMenuEntity menuEntity = new CompanyMenuEntity();
        menuEntity.setTitle(companyMenuDto.getTitle());
        menuEntity.setBranch(companyMenuDto.getBranch());
        menuEntity.setDescription(companyMenuDto.getDescription());
        menuEntity.setCompanyDataId(companyMenuDto.getCompanyDataId());
        menuEntity.setHeader(companyMenuDto.getHeader());
        menuEntity.setActive(companyMenuDto.getActive());
        menuEntity.setCreatedAt(companyMenuDto.getCreatedAt());
        menuEntity.setModifiedAt(companyMenuDto.getModifiedAt());
        menuEntity.setDeletedAt(companyMenuDto.getDeletedAt());
        return menuEntity;
    }

    public static CompanyMenuEntity updateFromDto(UpdateCompanyMenuDto dto, CompanyMenuEntity companyMenuEntity) {
        companyMenuEntity.setTitle(Optional.ofNullable(dto.getTitle()).orElse(companyMenuEntity.getTitle()));
        companyMenuEntity.setBranch(Optional.ofNullable(dto.getBranch()).orElse(companyMenuEntity.getBranch()));
        companyMenuEntity.setDescription(Optional.ofNullable(dto.getDescription()).orElse(companyMenuEntity.getDescription()));
        companyMenuEntity.setHeader(Optional.ofNullable(dto.getHeader()).orElse(companyMenuEntity.getHeader()));
        companyMenuEntity.setCreatedAt(Optional.ofNullable(dto.getCreatedAt()).orElse(companyMenuEntity.getCreatedAt()));
        companyMenuEntity.setModifiedAt(Optional.ofNullable(dto.getModifiedAt()).orElse(companyMenuEntity.getModifiedAt()));
        companyMenuEntity.setDeletedAt(Optional.ofNullable(dto.getDeletedAt()).orElse(companyMenuEntity.getDeletedAt()));
        companyMenuEntity.setActive(Optional.ofNullable(dto.getActive()).orElse(companyMenuEntity.getActive()));
        return companyMenuEntity;
    }
}
