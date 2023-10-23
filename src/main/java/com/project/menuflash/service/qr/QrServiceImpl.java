package com.project.menuflash.service.qr;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.entity.QrEntity;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.mapper.QrMapper;
import com.project.menuflash.repository.ItemMenuRepository;
import com.project.menuflash.repository.QrRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrServiceImpl implements QrService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final QrRepository qrRepository;

    public QrServiceImpl(QrRepository qrRepository) {
        this.qrRepository = qrRepository;
    }

    @Override
    public void createQr(List<CreateQrDto> createQrDto) throws Exception {
        try {
            List<QrEntity> qrEntity = createQrDto.stream().map(dto -> {
                QrEntity entity = QrMapper.dtoToEntity(dto);
                entity.setActive(Boolean.TRUE);
                entity.setCreatedAt(new Date());
                return entity;
            }).collect(Collectors.toList());
            qrRepository.saveAll(qrEntity);
        } catch (Exception e) {
            LOG.error("create QR error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear QR", e);
        }
    }

    @Override
    public List<QrResponse> getTableQrList(Long id) throws Exception {
        try {
            List<QrEntity> listQrTablesEntities = qrRepository.findAllByCompanyMenuId(id);
            return listQrTablesEntities.stream().map(QrMapper::entityToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("getTableQrList error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar qr de la sucursal", e);
        }
    }

}
