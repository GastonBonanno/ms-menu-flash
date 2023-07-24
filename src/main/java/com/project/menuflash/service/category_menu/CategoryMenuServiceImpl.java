package com.project.menuflash.service.category_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.entity.CategoryMenuEntity;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.mapper.CategoryMenuMapper;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.repository.CategoryMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CategoryMenuServiceImpl implements CategoryMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final CategoryMenuRepository categoryMenuRepository;

    public CategoryMenuServiceImpl(CategoryMenuRepository categoryMenuRepository) {
        this.categoryMenuRepository = categoryMenuRepository;
    }

    @Override
    public void createCategory(CreateCategoryMenuDto categoryMenuDto) throws Exception {
        try {
            categoryMenuRepository.save(CategoryMenuMapper.dtoToEntity(categoryMenuDto));
        } catch (Exception e) {
            LOG.error("create category error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear categoría de menú", e);
        }
    }

    public void updateCategoryMenu(UpdateCategoryMenuDto updateCategoryMenuDto, Long id) throws ResponseStatusException {
        try {
            CategoryMenuEntity categoryMenuEntity = getCategoryMenuEntityById(id);
            categoryMenuRepository.save(CategoryMenuMapper.updateFromDto(updateCategoryMenuDto,categoryMenuEntity));
        } catch (Exception e) {
            LOG.error("updateCategory error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar categoría", e);
        }
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        try {
            categoryMenuRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("deleteCategory error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar categoria", e);
        }
    }

    private CategoryMenuEntity getCategoryMenuEntityById(Long id) throws Exception {
        try {
            return categoryMenuRepository.findById(id).orElse(null);
        } catch (Exception e) {
            LOG.error("getCategoryMenuEntityById error: {}", e.getMessage());
            throw new Exception();
        }
    }
}
