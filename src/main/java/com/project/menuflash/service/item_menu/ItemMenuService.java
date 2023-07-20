package com.project.menuflash.service.item_menu;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.dto.response.FindItemMenuResponse;

public interface ItemMenuService {

  void createItemMenu(CreateItemMenuDto createItemMenuDto) throws Exception;

  void updateItemMenu (UpdateItemMenuDto updateItemMenuDto, Long id) throws Exception;

  void deleteItem(Long id) throws Exception;
}
