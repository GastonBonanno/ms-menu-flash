package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.entity.QrEntity;

import java.util.Optional;

public class QrMapper {

    public static QrEntity dtoToEntity(CreateQrDto dto){
        QrEntity qrEntity = new QrEntity();
        qrEntity.setTableName(dto.getTableName());
        qrEntity.setCompanyMenuId(dto.getCompanyMenuId());
        return qrEntity;
    }

    public static QrResponse entityToResponse(QrEntity entity){
        QrResponse qrResponse = new QrResponse();
        qrResponse.setTableName(entity.getTableName());
        qrResponse.setId(entity.getId());
        qrResponse.setCompanyMenuId(entity.getCompanyMenuId());
        return qrResponse;
    }
}
