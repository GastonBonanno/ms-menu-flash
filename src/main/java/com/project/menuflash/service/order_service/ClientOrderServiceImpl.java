package com.project.menuflash.service.order_service;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ClientUserEntity;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.ClientOrderMapper;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.repository.ClientOrderRepository;
import com.project.menuflash.repository.ItemMenuRepository;
import com.project.menuflash.repository.UserRepository;
import com.project.menuflash.service.item_menu.ItemMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ClientOrderRepository clientOrderRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public ClientOrderServiceImpl(ClientOrderRepository clientOrderRepository, TokenService tokenService, UserRepository userRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    public List<FindAllClientOrderResponse> findAllByCompanyMenuId(String authToken) throws Exception {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            ClientUserEntity clientUserEntity = userRepository.findByEmail(loggedUser.getEmail());
            List<ClientOrderEntity> clientOrderEntityList = clientOrderRepository
                    .findByCompanyMenuIdOrderByCreatedAtDesc(clientUserEntity.getCompanyDataEntity().getId());
            return clientOrderEntityList.stream().map(ClientOrderMapper::entityToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("findAllByCompanyMenuId error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar ordenes del usuario", e);
        }
    }
}
