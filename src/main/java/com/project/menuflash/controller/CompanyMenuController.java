package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.service.company_menu.CompanyMenuService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @GetMapping(value = "/{companyDataId}")
//    public ResponseEntity<FindCompanyMenuResponse> findByCompanyIdAndMenuId(@PathVariable Long companyDataId) throws Exception {
//        LOG.info("FindByUserId begins");
//        FindCompanyMenuResponse response = companyMenuService.getCompanyMenu(companyDataId);
//        LOG.info("FindByUserId ends with response: {} ", response);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping(value = "")
    public ResponseEntity<List<FindCompanyMenuResponse>> findByCompanyId(@RequestHeader("auth-token") String authToken) throws Exception {
        LOG.info("FindByUserId begins");
//        String authToken = requestEntity.getHeaders().getFirst("auth-token");
        List<FindCompanyMenuResponse> listResponse = companyMenuService.getCompanyMenu(authToken);
        LOG.info("FindByUserId ends with response: {} ", listResponse);
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateCompanyMenuResponse> create(@RequestBody CreateCompanyMenuDto createCompanyMenuDto,
                                                            @RequestHeader("auth-token") String authToken) throws Exception {
        LOG.info("Create begins");
        CreateCompanyMenuResponse createCompanyMenuResponse = companyMenuService.createMenu(createCompanyMenuDto, authToken);
        LOG.info("finished");
        return new ResponseEntity<>(createCompanyMenuResponse, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateCompanyMenuDto updateCompanyMenuDto, @PathVariable Long id) throws Exception {
        LOG.info("Update begins with state: {} and with id: {}", updateCompanyMenuDto,id);
        companyMenuService.updateCompanyMenu(updateCompanyMenuDto,id);
        LOG.info("Update ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        LOG.info("Delete begins with id: {}", id);
        companyMenuService.deleteMenu(id);
        LOG.info("Delete ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
