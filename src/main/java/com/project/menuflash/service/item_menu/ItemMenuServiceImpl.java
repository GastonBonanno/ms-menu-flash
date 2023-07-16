package com.project.menuflash.service.item_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.repository.ItemMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ItemMenuRepository itemMenuRepository;

    public ItemMenuServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public void createItemMenu(CreateItemMenuDto itemMenuDto) throws Exception {
        try {
            itemMenuRepository.save(ItemMenuMapper.dtoToEntity(itemMenuDto));
        } catch (Exception e) {
            LOG.error("create item error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear item de categoría", e);
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
}
