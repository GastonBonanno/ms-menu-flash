package com.project.menuflash.service.order_service;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateItemMenuDto;
import com.project.menuflash.dto.request.CreateOrderDto;
import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateItemMenuDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.ItemMenuResponse;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.entity.*;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.ClientOrderMapper;
import com.project.menuflash.mapper.ItemMenuMapper;
import com.project.menuflash.mapper.QrMapper;
import com.project.menuflash.repository.ClientOrderRepository;
import com.project.menuflash.repository.ItemMenuRepository;
import com.project.menuflash.repository.StateRepository;
import com.project.menuflash.repository.UserRepository;
import com.project.menuflash.service.item_menu.ItemMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ClientOrderRepository clientOrderRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    private final StateRepository stateRepository;

    public ClientOrderServiceImpl(ClientOrderRepository clientOrderRepository, TokenService tokenService, UserRepository userRepository, StateRepository stateRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar ordenes de la compañia", e);
        }
    }

    @Override
    public List<FindAllClientOrderResponse> findAllByClientEmail(String authToken) throws Exception {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            ClientUserEntity clientUserEntity = userRepository.findByEmail(loggedUser.getEmail());
            List<ClientOrderEntity> clientOrderEntityList = clientOrderRepository.findByClientEmailOrderByCreatedAtDesc(clientUserEntity.getEmail());
            return clientOrderEntityList.stream().map(ClientOrderMapper::entityToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("findAllByClientEmail error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar ordenes del usuario", e);
        }
    }

    @Override
    public void updateOrderState(Long id, String state) throws Exception {
        Optional<ClientOrderEntity> clientOrderEntity = clientOrderRepository.findById(id);
        List<StateEntity> stateEntityList = stateRepository.findAll();
        Optional<StateEntity> stateEntity = stateEntityList.stream().filter(s -> state.equals(s.getName())).findFirst();
        if(stateEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al modificar estado. Estado incorrecto");

        clientOrderEntity.get().setStateEntity(stateEntity.get());
        clientOrderRepository.save(clientOrderEntity.get());
    }

    @Override
    public void createOrder(CreateOrderDto createOrderDto) throws Exception {
        try {
            ClientOrderEntity orderEntity = ClientOrderMapper.dtoToEntity(createOrderDto);
            orderEntity.setActive(Boolean.TRUE);
            orderEntity.setCreatedAt(new Date());
            clientOrderRepository.save(orderEntity);
        } catch (Exception e) {
            LOG.error("create order error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear su orden", e);
        }
    }
}
