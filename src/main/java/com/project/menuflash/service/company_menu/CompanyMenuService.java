package com.project.menuflash.service.company_menu;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;

import java.util.List;

public interface CompanyMenuService {
  List<FindCompanyMenuResponse> getCompanyMenu(String authToken) throws Exception;

  FindCompanyMenuResponse getMenuById(Long menuId) throws Exception;

  CreateCompanyMenuResponse createMenu(CreateCompanyMenuDto companyMenuDto, String authToken) throws Exception;

  void deleteMenu(Long id) throws Exception;

  void updateCompanyMenu(UpdateCompanyMenuDto updateCompanyMenuDto, Long id);
}
