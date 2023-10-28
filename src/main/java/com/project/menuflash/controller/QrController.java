package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.dto.response.CategoryMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.service.category_menu.CategoryMenuService;
import com.project.menuflash.service.qr.QrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/qr")
public class QrController {
    private final QrService qrService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(QrController.class);

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<QrResponse>> findByCompanyMenuId(@PathVariable Long id) throws Exception {
        LOG.info("findByCompanyMenuId begins");
        List<QrResponse> response = qrService.getTableQrList(id);
        LOG.info("findByCompanyMenuId ends with response: {} ", response);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<QrResponse> createQr(@RequestBody List<CreateQrDto> createQrDto) throws Exception {
        LOG.info("createQr begins");
        qrService.createQr(createQrDto);
        LOG.info("createQr ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        LOG.info("Delete begins with id: {}", id);
        qrService.deleteQr(id);
        LOG.info("Delete ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
