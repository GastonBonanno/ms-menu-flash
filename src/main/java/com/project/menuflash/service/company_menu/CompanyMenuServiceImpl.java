package com.project.menuflash.service.company_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.request.UpdateCompanyMenuDto;
import com.project.menuflash.dto.response.CreateCompanyMenuResponse;
import com.project.menuflash.dto.response.FindCompanyMenuResponse;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.repository.CompanyDataRepository;
import com.project.menuflash.repository.CompanyMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyMenuServiceImpl implements CompanyMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final CompanyMenuRepository companyMenuRepository;
    private final TokenService tokenService;


    public CompanyMenuServiceImpl(CompanyMenuRepository companyMenuRepository, CompanyDataRepository companyDataRepository, TokenService tokenService) {
        this.companyMenuRepository = companyMenuRepository;
        this.tokenService = tokenService;
    }

    public List<FindCompanyMenuResponse> getCompanyMenu(String authToken) throws ResponseStatusException {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            List<CompanyMenuEntity> listCompanyMenuEntities = companyMenuRepository.findByActiveAndCompanyDataId(Boolean.TRUE, loggedUser.getId());
            return listCompanyMenuEntities.stream().map(CompanyMenuMapper::companyMenuEntityToFindCompanyMenuResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("getCompanyMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar menu de empresa", e);
        }
    }

    public FindCompanyMenuResponse getMenuById(Long menuId) throws ResponseStatusException {
        try {
            FindCompanyMenuResponse findCompanyMenuResponse = null;
            Optional<CompanyMenuEntity> companyMenuEntity = companyMenuRepository.findById(menuId);
            if(companyMenuEntity.isPresent())
                findCompanyMenuResponse = CompanyMenuMapper.companyMenuEntityToFindCompanyMenuResponse(companyMenuEntity.get());
            return findCompanyMenuResponse;
        } catch (Exception e) {
            LOG.error("getCompanyMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar menu de empresa", e);
        }
    }

    @Override
    public CreateCompanyMenuResponse createMenu(CreateCompanyMenuDto companyMenuDto, String authToken) throws Exception {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            companyMenuDto.setActive(Boolean.TRUE);
            companyMenuDto.setCreatedAt(new Date());
            companyMenuDto.setCompanyDataId(loggedUser.getId());
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
