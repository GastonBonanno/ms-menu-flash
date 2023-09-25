package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.RegisterUserDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyDataEntity;
import com.project.menuflash.entity.CompanyMenuEntity;

import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyDataMapper {

    public static CompanyDataEntity dtoToEntity(RegisterUserDto registerUserDto){
        CompanyDataEntity companyDataEntity = new CompanyDataEntity();
        return companyDataEntity;
    }
}
