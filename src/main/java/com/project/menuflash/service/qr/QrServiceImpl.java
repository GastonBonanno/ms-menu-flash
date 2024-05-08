package com.project.menuflash.service.qr;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.entity.QrEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.QrMapper;
import com.project.menuflash.repository.CompanyDataRepository;
import com.project.menuflash.repository.QrRepository;
import com.project.menuflash.util.DatesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrServiceImpl implements QrService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final QrRepository qrRepository;
    private final TokenService tokenService;
    private final CompanyDataRepository companyDataRepository;

    public QrServiceImpl(QrRepository qrRepository, TokenService tokenService, CompanyDataRepository companyDataRepository) {
        this.qrRepository = qrRepository;
        this.tokenService = tokenService;
        this.companyDataRepository = companyDataRepository;
    }

    @Override
    public void createQr(List<CreateQrDto> createQrDto, String authToken) throws Exception {
        try {
            var loggedUser = tokenService.getUserFromToken(authToken);
            var companyDataEntity = companyDataRepository.findByClientUserId(loggedUser.getId());
            List<QrEntity> qrEntity = createQrDto.stream().map(dto -> {
                dto.setCompanyId(companyDataEntity.getId());
                QrEntity entity = QrMapper.dtoToEntity(dto);
                entity.setActive(Boolean.TRUE);
                entity.setCreatedAt(DatesUtil.getTodayUtcArg());
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
            List<QrEntity> listQrTablesEntities = qrRepository.findAllByCompanyMenuIdOrderByTableName(id);
            return listQrTablesEntities.stream().map(QrMapper::entityToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("getTableQrList error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar qr de la sucursal", e);
        }
    }

    @Override
    public void deleteQr(Long id) throws Exception {
        try {
            qrRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("deleteMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar QR", e);
        }
    }

}
