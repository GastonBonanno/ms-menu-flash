package com.project.menuflash.service.qr;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.QrResponse;

public interface QrService {

  void createQr(CreateQrDto createQrDto) throws Exception;

}
