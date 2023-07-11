package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.service.company_menu.CompanyMenuService;
import com.project.menuflash.service.item_menu.ItemMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/menu-item")
public class ItemMenuController {
    private final ItemMenuService itemMenuService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(ItemMenuController.class);

    public ItemMenuController(ItemMenuService itemMenuService) {
        this.itemMenuService = itemMenuService;
    }
//    @GetMapping
//    public ResponseEntity<List<FindCompanyMenuResponse>> findAll() throws Exception {
//        LOG.info("FindAll begins");
//        List<FindCompanyMenuResponse> response = companyMenuService.getCompanyMenu();
//        LOG.info("FindAll ends with response: {} ", response);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateItemMenuDto createItemMenuDto) throws Exception {
        LOG.info("Create begins");
        itemMenuService.createItemMenu(createItemMenuDto);
        LOG.info("finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
