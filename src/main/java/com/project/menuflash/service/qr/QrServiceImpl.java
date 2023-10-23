package com.project.menuflash.service.qr;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.entity.QrEntity;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.mapper.QrMapper;
import com.project.menuflash.repository.ItemMenuRepository;
import com.project.menuflash.repository.QrRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class QrServiceImpl implements QrService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final QrRepository qrRepository;

    public QrServiceImpl(QrRepository qrRepository) {
        this.qrRepository = qrRepository;
    }

    @Override
    public void createQr(CreateQrDto createQrDto) throws Exception {
        try {
            QrEntity qrEntity = QrMapper.dtoToEntity(createQrDto);
            qrEntity.setActive(Boolean.TRUE);
            qrEntity.setCreatedAt(new Date());
            qrRepository.save(qrEntity);
        } catch (Exception e) {
            LOG.error("create QR error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear QR", e);
        }
    }

}
