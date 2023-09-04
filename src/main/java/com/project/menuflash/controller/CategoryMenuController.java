package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.service.category_menu.CategoryMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/category-menu")
public class CategoryMenuController {
    private final CategoryMenuService categoryMenuService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(CategoryMenuController.class);

    public CategoryMenuController(CategoryMenuService categoryMenuService) {
        this.categoryMenuService = categoryMenuService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<CreateCategoryMenuDto> listCreateCategoryMenuDto) throws Exception {
        LOG.info("Create begins");
        categoryMenuService.createCategory(listCreateCategoryMenuDto);
        LOG.info("finished");
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateCategoryMenuDto updateCategoryMenuDto, @PathVariable Long id) throws Exception {
        LOG.info("Update begins with state: {} and with id: {}", updateCategoryMenuDto,id);
        categoryMenuService.updateCategoryMenu(updateCategoryMenuDto,id);
        LOG.info("Update ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        LOG.info("Delete begins with id: {}", id);
        categoryMenuService.deleteCategory(id);
        LOG.info("Delete ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
