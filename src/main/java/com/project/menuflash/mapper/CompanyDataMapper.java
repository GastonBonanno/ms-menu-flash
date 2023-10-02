package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.RegisterUserDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyDataEntity;
import com.project.menuflash.entity.CompanyMenuEntity;

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
}
