package com.project.menuflash.service.company_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.repository.CompanyMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompanyMenuServiceImpl implements CompanyMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final CompanyMenuRepository companyMenuRepository;


    public CompanyMenuServiceImpl(CompanyMenuRepository companyMenuRepository) {
        this.companyMenuRepository = companyMenuRepository;
    }

    public FindCompanyMenuResponse getCompanyMenu(Long companyDataId) throws ResponseStatusException {
        try {
            CompanyMenuEntity companyMenuEntities = companyMenuRepository.findByActiveAndCompanyDataId(Boolean.TRUE,companyDataId);
            return CompanyMenuMapper.companyMenuEntityToFindCompanyMenuResponse(companyMenuEntities);
        } catch (Exception e) {
            LOG.error("getCompanyMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar menu de empresa", e);
        }
    }

    @Override
    public void createMenu(CreateCompanyMenuDto companyMenuDto) throws Exception {
        try {
            companyMenuRepository.save(CompanyMenuMapper.dtoToEntity(companyMenuDto));
        } catch (Exception e) {
            LOG.error("create menu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear menu de empresa", e);
        }

    }
}
