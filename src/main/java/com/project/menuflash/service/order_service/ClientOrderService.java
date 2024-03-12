package com.project.menuflash.service.order_service;

import com.project.menuflash.dto.request.CreateOrderDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.entity.ClientOrderEntity;

import java.util.List;

public interface ClientOrderService {

   List<FindAllClientOrderResponse> findAllByCompanyMenuId(String authToken) throws Exception;
   List<FindAllClientOrderResponse> findAllByClientEmail(String authToken) throws Exception;

   void updateOrderState(Long id, String state) throws Exception;

   void createOrder(CreateOrderDto createOrderDto) throws Exception;
}
