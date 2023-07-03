package com.project.menuflash.service.company_menu;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindAllCompanyMenuResponse;

import java.util.List;

public interface CompanyMenuService {
  List<FindAllCompanyMenuResponse> getCompanyMenu() throws Exception;

  void createMenu(CreateCompanyMenuDto companyMenuDto) throws Exception;
}
