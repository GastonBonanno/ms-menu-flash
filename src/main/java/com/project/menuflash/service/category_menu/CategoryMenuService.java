package com.project.menuflash.service.category_menu;

import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;

import java.util.List;

public interface CategoryMenuService {
//  List<FindAllCompanyMenuResponse> getCategoryMenu() throws Exception;

  void createCategory(List<CreateCategoryMenuDto> listCreateCategoryMenuDto) throws Exception;

  void deleteCategory(Long id) throws Exception;

  void updateCategoryMenu(UpdateCategoryMenuDto updateCategoryMenuDto, Long id);
}
