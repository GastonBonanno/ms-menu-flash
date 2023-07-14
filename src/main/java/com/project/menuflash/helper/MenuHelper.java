package com.project.menuflash.helper;

import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.mapper.CompanyMenuMapper;

import java.util.stream.Collectors;

public class MenuHelper {

    // FALTA AGREGAR EN ESTE METODO QUE TRAIGA TANTO LAS LIST DE CATEGORIAS COMO LA DE ITEMS
    public static FindCompanyMenuResponse companyMenuEntityToFindCompanyMenuResponse(CompanyMenuEntity companyMenuEntity){
        FindCompanyMenuResponse findCompanyMenuResponse = new FindCompanyMenuResponse();
        findCompanyMenuResponse.setId(companyMenuEntity.getId());
        findCompanyMenuResponse.setTitle(companyMenuEntity.getTitle());
        findCompanyMenuResponse.setDescription(companyMenuEntity.getDescription());
        findCompanyMenuResponse.setHeader(companyMenuEntity.getHeader());
        findCompanyMenuResponse.setFooter(companyMenuEntity.getFooter());
        findCompanyMenuResponse.setClientUserId(companyMenuEntity.getClientUserId());
        findCompanyMenuResponse.setActive(companyMenuEntity.getActive());
        findCompanyMenuResponse.setCreatedAt(companyMenuEntity.getCreatedAt());
        findCompanyMenuResponse.setModifiedAt(companyMenuEntity.getModifiedAt());
        findCompanyMenuResponse.setDeletedAt(companyMenuEntity.getDeletedAt());
        findCompanyMenuResponse.setCategories(companyMenuEntity.getCategories().stream().map(CompanyMenuMapper::mapperToResponse).collect(Collectors.toList()));
        return findCompanyMenuResponse;
    }
}
