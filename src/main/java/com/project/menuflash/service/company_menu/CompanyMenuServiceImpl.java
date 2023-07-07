package com.project.menuflash.service.company_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCompanyMenuDto;
import com.project.menuflash.dto.response.FindAllCompanyMenuResponse;
import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.repository.CompanyMenuRepository;
import com.sun.tools.jconsole.JConsoleContext;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyMenuServiceImpl implements CompanyMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);
    private final CompanyMenuRepository companyMenuRepository;


    public CompanyMenuServiceImpl(CompanyMenuRepository companyMenuRepository) {
        this.companyMenuRepository = companyMenuRepository;
    }

    public List<FindAllCompanyMenuResponse> getCompanyMenu() throws ResponseStatusException {
        try {
            List<CompanyMenuEntity> companyMenuEntities = companyMenuRepository.findByActive(Boolean.TRUE);
            return companyMenuEntities.stream().map(CompanyMenuEntity::toResponseDto).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("getCompanyMenu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar menu de empresa", e);
        }
    }

    @Override
    public void createMenu(CreateCompanyMenuDto companyMenuDto) throws Exception {
        try {
            System.out.println("DTOOOO: " + companyMenuDto);
            companyMenuRepository.save(CompanyMenuMapper.dtoToEntity(companyMenuDto));
        } catch (Exception e) {
            LOG.error("create menu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear menu de empresa", e);
        }

    }
}
