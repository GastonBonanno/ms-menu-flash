package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.service.company_menu.CompanyMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/company-menu")
public class CompanyMenuController {
    private final CompanyMenuService companyMenuService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(com.project.menuflash.controller.CompanyMenuController.class);

    public CompanyMenuController(CompanyMenuService companyMenuService) {
        this.companyMenuService = companyMenuService;
    }
    @GetMapping(value = "/{clientUserId}")
    public ResponseEntity<FindCompanyMenuResponse> findByUserId(@PathVariable Long clientUserId) throws Exception {
        LOG.info("FindByUserId begins");
        FindCompanyMenuResponse response = companyMenuService.getCompanyMenu(clientUserId);
        LOG.info("FindByUserId ends with response: {} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCompanyMenuDto createCompanyMenuDto) throws Exception {
        LOG.info("Create begins");
        companyMenuService.createMenu(createCompanyMenuDto);
        LOG.info("finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
