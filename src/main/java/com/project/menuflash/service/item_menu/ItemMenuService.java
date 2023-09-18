package com.project.menuflash.service.item_menu;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;

public interface ItemMenuService {

  ItemMenuResponse createItemMenu(CreateItemMenuDto createItemMenuDto) throws Exception;

  void updateItemMenu (UpdateItemMenuDto updateItemMenuDto) throws Exception;

  void deleteItem(Long id) throws Exception;
}
