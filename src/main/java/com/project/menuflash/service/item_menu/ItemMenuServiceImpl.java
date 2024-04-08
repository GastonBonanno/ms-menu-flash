package com.project.menuflash.service.item_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.repository.ItemMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ItemMenuRepository itemMenuRepository;

    public ItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public ItemMenuResponse createItemMenu(CreateItemMenuDto itemMenuDto) throws Exception {
        try {
            itemMenuDto.setActive(Boolean.TRUE);
            itemMenuDto.setCreatedAt(new Date());
            ItemMenuEntity itemMenuEntity = itemMenuRepository.save(ItemMenuMapper.dtoToEntity(itemMenuDto));
            return ItemMenuMapper.entityToResponse(itemMenuEntity);
        } catch (Exception e) {
            LOG.error("create item error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear item de categoría", e);
        }
    }


    public void updateItemMenu(UpdateItemMenuDto updateItemMenuDto) throws ResponseStatusException {
        try {
            ItemMenuEntity itemMenuEntity = getItemMenuEntityById(updateItemMenuDto.getId());
            itemMenuRepository.save(ItemMenuMapper.updateDtoToEntity(updateItemMenuDto,itemMenuEntity));
        } catch (Exception e) {
            LOG.error("updateState error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar estado", e);
        }
    }


    @Override
    public void deleteItem(Long id) throws Exception {
        try {
            itemMenuRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("deleteItem error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar item", e);
        }

    }

    @Override
    public ItemMenuEntity getItemMenuEntityById(Long id) throws Exception {
        try {
            return itemMenuRepository.findById(id).orElse(null);
        } catch (Exception e) {
            LOG.error("getStateEntityById error: {}", e.getMessage());
            throw new Exception();
        }
    }
}
