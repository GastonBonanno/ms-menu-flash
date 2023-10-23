package com.project.menuflash.service.qr;

import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.QrResponse;

import java.util.List;

public interface QrService {

  void createQr(List<CreateQrDto> createQrDto) throws Exception;

  List<QrResponse> getTableQrList(Long id) throws Exception;

}
