package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.UpdateCompanyDataDto;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.entity.CompanyDataEntity;

import java.util.Optional;

public class CompanyDataMapper {

    public static CompanyDataResponse entityToResponse(CompanyDataEntity companyDataEntity){
        CompanyDataResponse companyDataResponse = new CompanyDataResponse();
        companyDataResponse.setName(companyDataEntity.getName());
        companyDataResponse.setCuit(companyDataEntity.getCuit());
        companyDataResponse.setAddress(companyDataEntity.getAddress());
        companyDataResponse.setPhoneNumber(companyDataEntity.getPhoneNumber());
        companyDataResponse.setPublicKey(companyDataEntity.getPublicKey());
        companyDataResponse.setAccessToken(companyDataEntity.getAccessToken());
        return companyDataResponse;
    }

    public static CompanyDataEntity updateDtoToEntity(UpdateCompanyDataDto dto, CompanyDataEntity companyDataEntity) {
        companyDataEntity.setName(Optional.ofNullable(dto.getName()).orElse(companyDataEntity.getName()));
        companyDataEntity.setCuit(Optional.ofNullable(dto.getCuit()).orElse(companyDataEntity.getCuit()));
        companyDataEntity.setAddress(Optional.ofNullable(dto.getAddress()).orElse(companyDataEntity.getAddress()));
        companyDataEntity.setPhoneNumber(Optional.ofNullable(dto.getPhoneNumber()).orElse(companyDataEntity.getPhoneNumber()));
        companyDataEntity.setPublicKey(Optional.ofNullable(dto.getPublicKey()).orElse(companyDataEntity.getPublicKey()));
        companyDataEntity.setAccessToken(Optional.ofNullable(dto.getAccessToken()).orElse(companyDataEntity.getAccessToken()));
        return companyDataEntity;
    }

}
