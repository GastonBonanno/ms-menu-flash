package com.project.menuflash.service.category_menu;

import com.project.menuflash.dto.request.CreateCategoryMenuDto;

public interface CategoryMenuService {
//  List<FindAllCompanyMenuResponse> getCategoryMenu() throws Exception;

  void createCategory(CreateCategoryMenuDto createCategoryMenuDto) throws Exception;
}
