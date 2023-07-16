package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.service.item_menu.ItemMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/item-menu")
public class ItemMenuController {
    private final ItemMenuService itemMenuService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(ItemMenuController.class);

    public ItemMenuController(ItemMenuService itemMenuService) {
        this.itemMenuService = itemMenuService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateItemMenuDto createItemMenuDto) throws Exception {
        LOG.info("Create begins");
        itemMenuService.createItemMenu(createItemMenuDto);
        LOG.info("finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        LOG.info("Delete begins with id: {}", id);
        itemMenuService.deleteItem(id);
        LOG.info("Delete ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
