package com.project.menuflash.controller;

import com.project.menuflash.dto.response.MercadopagoResponse;
import com.project.menuflash.service.mercadopago.MercadopagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/mercadopago")
public class MercadopagoController {
    private final MercadopagoService mercadopagoService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(MercadopagoController.class);

    public MercadopagoController(MercadopagoService mercadopagoService) {
        this.mercadopagoService = mercadopagoService;
    }

    @PostMapping(path = "/create-preference")
    public ResponseEntity<MercadopagoResponse> createPreference() throws Exception {
        LOG.info("createPreference begins");
        String response = mercadopagoService.createPreference();
        LOG.info("Finished");
        return new ResponseEntity<>(MercadopagoResponse.builder().id(response).build(), HttpStatus.OK);
    }


}
