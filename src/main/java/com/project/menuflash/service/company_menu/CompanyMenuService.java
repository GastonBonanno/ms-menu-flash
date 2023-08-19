package com.project.menuflash.service.company_menu;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;

public interface CompanyMenuService {
  FindCompanyMenuResponse getCompanyMenu(Long clientUserId) throws Exception;

  CreateCompanyMenuResponse createMenu(CreateCompanyMenuDto companyMenuDto) throws Exception;

  void deleteMenu(Long id) throws Exception;

  void updateCompanyMenu(UpdateCompanyMenuDto updateCompanyMenuDto, Long id);
}
