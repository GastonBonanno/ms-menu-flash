package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.service.category_menu.CategoryMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody CreateCategoryMenuDto createCategoryMenuDto) throws Exception {
        LOG.info("Create begins");
        categoryMenuService.createCategory(createCategoryMenuDto);
        LOG.info("finished");
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
