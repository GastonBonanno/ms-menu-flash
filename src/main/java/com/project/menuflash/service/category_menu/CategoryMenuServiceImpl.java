package com.project.menuflash.service.category_menu;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateCategoryMenuDto;
import com.project.menuflash.dto.response.FindCategoryMenuResponse;
import com.project.menuflash.mapper.CategoryMenuMapper;
import com.project.menuflash.mapper.CompanyMenuMapper;
import com.project.menuflash.repository.CategoryMenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMenuServiceImpl implements CategoryMenuService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final CategoryMenuRepository categoryMenuRepository;


    public CategoryMenuServiceImpl(CategoryMenuRepository categoryMenuRepository) {
        this.categoryMenuRepository = categoryMenuRepository;
    }

//    public List<FindCategoryMenuResponse> getCompanyMenu() throws ResponseStatusException {
//        try {
//            List<CategoryMenuEntities> categoryMenuEntities = categoryMenuRepository.findByActiveAndId(Boolean.TRUE);
//            return companyMenuEntities.stream().map(CompanyMenuMapper::entityToResponse).collect(Collectors.toList());
//        } catch (Exception e) {
//            LOG.error("getCompanyMenu error: {}", e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar menu de empresa", e);
//        }
//    }

    @Override
    public void createCategory(CreateCategoryMenuDto categoryMenuDto) throws Exception {
        try {
            categoryMenuRepository.save(CategoryMenuMapper.dtoToEntity(categoryMenuDto));
        } catch (Exception e) {
            LOG.error("create menu error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear menu de empresa", e);
        }
    }
}
