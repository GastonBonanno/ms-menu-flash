package com.project.menuflash.controller;

import com.project.menuflash.dto.request.MercadopagoDto;
import com.project.menuflash.dto.response.MercadopagoResponse;
import com.project.menuflash.service.mercadopago.MercadopagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MercadopagoResponse> createPreference(@RequestBody List<MercadopagoDto> mercadopagoDto,
                                                                @RequestParam Long companyId,
                                                                @RequestParam Long orderId) throws Exception {
        LOG.info("createPreference begins");
        String response = mercadopagoService.createPreference(mercadopagoDto, companyId, orderId);
        LOG.info("Finished");
        return new ResponseEntity<>(MercadopagoResponse.builder().id(response).build(), HttpStatus.OK);
    }


}
