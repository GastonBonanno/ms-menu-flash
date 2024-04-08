package com.project.menuflash.service.mercadopago;

import com.project.menuflash.dto.request.MercadopagoDto;

import java.util.List;

public interface MercadopagoService {

    String createPreference(List<MercadopagoDto> mercadopagoDto, Long companyId, Long orderId) throws Exception;

}
