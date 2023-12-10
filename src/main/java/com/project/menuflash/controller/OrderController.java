package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.service.item_menu.ItemMenuService;
import com.project.menuflash.service.order_service.ClientOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/order")
public class OrderController {
    private final ClientOrderService clientOrderService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(OrderController.class);

    public OrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @GetMapping
    public ResponseEntity<List<FindAllClientOrderResponse>> findAll(@RequestHeader("auth-token") String authToken) throws Exception {
        LOG.info("Create begins");
        List<FindAllClientOrderResponse> clientOrderResponse= clientOrderService.findAllByCompanyMenuId(authToken);
        LOG.info("finished");
        return new ResponseEntity<>(clientOrderResponse,HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateState(@PathVariable Long id, @RequestParam String state) throws Exception {
        LOG.info("Update order state begins with id: {}", id);
        clientOrderService.updateOrderState(id, state);
        LOG.info("update state ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
