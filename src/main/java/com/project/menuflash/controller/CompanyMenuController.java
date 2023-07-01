package com.project.menuflash.controller;

import com.project.menuflash.dto.response.FindAllCompanyMenuResponse;
import com.project.menuflash.service.company_menu.CompanyMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/company-menu")
public class CompanyMenuController {
    private final CompanyMenuService companyMenuService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(com.project.menuflash.controller.CompanyMenuController.class);

    public CompanyMenuController(CompanyMenuService companyMenuService) {
        this.companyMenuService = companyMenuService;
    }
    @GetMapping
    public ResponseEntity<List<FindAllCompanyMenuResponse>> findAll() throws Exception {
        LOG.info("FindAll begins");
        List<FindAllCompanyMenuResponse> response = companyMenuService.getCompanyMenu();
        LOG.info("FindAll ends with response: {} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
