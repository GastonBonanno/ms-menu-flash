package com.project.menuflash.service.item_menu;

import com.project.menuflash.dto.request.CreateItemMenuDto;

public interface ItemMenuService {
//  List<FindAllCompanyMenuResponse> getCategoryMenu() throws Exception;

  void createItemMenu(CreateItemMenuDto createItemMenuDto) throws Exception;

  void deleteItem(Long id) throws Exception;
}
