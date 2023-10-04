package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.*;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyDataEntity;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.entity.ItemMenuEntity;

import javax.persistence.Column;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyDataMapper {

    public static CompanyDataResponse entityToResponse(CompanyDataEntity companyDataEntity){
        CompanyDataResponse companyDataResponse = new CompanyDataResponse();
        companyDataResponse.setName(companyDataEntity.getName());
        companyDataResponse.setCuit(companyDataEntity.getCuit());
        companyDataResponse.setAddress(companyDataEntity.getAddress());
        companyDataResponse.setPhoneNumber(companyDataEntity.getPhoneNumber());
        return companyDataResponse;
    }

    public static CompanyDataEntity updateDtoToEntity(UpdateCompanyDataDto dto, CompanyDataEntity companyDataEntity) {
        companyDataEntity.setName(Optional.ofNullable(dto.getName()).orElse(companyDataEntity.getName()));
        companyDataEntity.setCuit(Optional.ofNullable(dto.getCuit()).orElse(companyDataEntity.getCuit()));
        companyDataEntity.setAddress(Optional.ofNullable(dto.getAddress()).orElse(companyDataEntity.getAddress()));
        companyDataEntity.setPhoneNumber(Optional.ofNullable(dto.getPhoneNumber()).orElse(companyDataEntity.getPhoneNumber()));
        return companyDataEntity;
    }

}
