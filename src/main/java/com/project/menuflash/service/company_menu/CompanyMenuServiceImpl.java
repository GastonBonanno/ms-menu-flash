package com.project.menuflash.service.company_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCategoryMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.entity.CategoryMenuEntity;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.mapper.CategoryMenuMapper;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.repository.CompanyMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

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
    public CreateCompanyMenuResponse createMenu(CreateCompanyMenuDto companyMenuDto) throws Exception {
        try {
            companyMenuDto.setActive(Boolean.TRUE);
            companyMenuDto.setCreatedAt(new Date());
            CompanyMenuEntity companyMenuEntity = companyMenuRepository.save(CompanyMenuMapper.dtoToEntity(companyMenuDto));
            return CompanyMenuMapper.entityToResponse(companyMenuEntity);
        } catch (Exception e) {
            LOG.error("create menu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear menu de empresa", e);
        }
    }

    public void updateCompanyMenu(UpdateCompanyMenuDto updateCompanyMenuDto, Long id) throws ResponseStatusException {
        try {
            CompanyMenuEntity companyMenuEntity = getCompanyMenuEntityById(id);
            companyMenuRepository.save(CompanyMenuMapper.updateFromDto(updateCompanyMenuDto,companyMenuEntity));
        } catch (Exception e) {
            LOG.error("updateCategory error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar categoría", e);
        }
    }

    @Override
    public void deleteMenu(Long id) throws Exception {
        try {
            companyMenuRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("deleteMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar menu", e);
        }
    }

    private CompanyMenuEntity getCompanyMenuEntityById(Long id) throws Exception {
        try {
            return companyMenuRepository.findById(id).orElse(null);
        } catch (Exception e) {
            LOG.error("getCompanyMenuEntityById error: {}", e.getMessage());
            throw new Exception();
        }
    }
}
