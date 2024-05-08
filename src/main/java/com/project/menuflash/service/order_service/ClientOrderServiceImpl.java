package com.project.menuflash.service.order_service;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.ClientOrderItemDto;
import com.project.menuflash.dto.request.CreateOrderDto;
import com.project.menuflash.dto.response.FindAllClientOrderResponse;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ClientOrderItemEntity;
import com.project.menuflash.entity.ClientUserEntity;
import com.project.menuflash.entity.StateEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.ClientOrderMapper;
import com.project.menuflash.repository.ClientOrderItemRepository;
import com.project.menuflash.repository.ClientOrderRepository;
import com.project.menuflash.repository.StateRepository;
import com.project.menuflash.repository.UserRepository;
import com.project.menuflash.util.DatesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ClientOrderRepository clientOrderRepository;
    private final ClientOrderItemRepository clientOrderItemRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    private final StateRepository stateRepository;

    public ClientOrderServiceImpl(ClientOrderRepository clientOrderRepository, ClientOrderItemRepository clientOrderItemRepository, TokenService tokenService, UserRepository userRepository, StateRepository stateRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.clientOrderItemRepository = clientOrderItemRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public List<FindAllClientOrderResponse> findAllByCompanyMenuId(String authToken, Long menuId) throws Exception {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            ClientUserEntity clientUserEntity = userRepository.findByEmail(loggedUser.getEmail());
            List<ClientOrderEntity> clientOrderEntityList = clientOrderRepository
                    .findByCompanyMenuIdAndActiveAndCompanyMenuIdOrderByCreatedAtDesc(clientUserEntity.getCompanyDataEntity().getId(),
                            Boolean.TRUE,
                            menuId);
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
            List<ClientOrderEntity> clientOrderEntityList = clientOrderRepository.findByClientEmailAndActiveOrderByCreatedAtDesc(clientUserEntity.getEmail(), Boolean.TRUE);
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
    public FindAllClientOrderResponse activateOrder(Long id, boolean active) throws Exception {
        Optional<ClientOrderEntity> clientOrderEntity = clientOrderRepository.findById(id);
        if(clientOrderEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se encontró la orden para activar");
        clientOrderEntity.get().setActive(active);
        clientOrderRepository.save(clientOrderEntity.get());
        return ClientOrderMapper.entityToResponse(clientOrderEntity.get());
    }

    @Override
    public FindAllClientOrderResponse createOrder(CreateOrderDto createOrderDto, String authToken) throws Exception {
        try {
            LoggedUser loggedUser = tokenService.getUserFromToken(authToken);
            ClientOrderEntity orderEntity = ClientOrderMapper.dtoToEntity(createOrderDto);
            orderEntity.setOrderId(orderIdRandom());
            orderEntity.setClientEmail(loggedUser.getEmail());
            StateEntity state = stateRepository.findByName("PENDIENTE");
            orderEntity.setStateEntity(state);
            orderEntity.setActive(Boolean.FALSE);
            orderEntity.setCreatedAt(DatesUtil.getTodayUtcArg());
            orderEntity.setClientOrderItemEntityList(new ArrayList<>());
            ClientOrderEntity newOrder = clientOrderRepository.save(orderEntity);
            createOrderItemList(newOrder, createOrderDto);
            return ClientOrderMapper.entityToResponse(newOrder);
        } catch (Exception e) {
            LOG.error("create order error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear su orden", e);
        }
    }

    private void createOrderItemList( ClientOrderEntity newOrder, CreateOrderDto createOrderDto) {
        List<ClientOrderItemEntity> clientOrderItemEntityList = new ArrayList<>();
        for(ClientOrderItemDto orderItemList : createOrderDto.getClientOrderItemDto()){
            ClientOrderItemEntity newClientOrderItemEntity = new ClientOrderItemEntity();
            newClientOrderItemEntity.setClientOrderId(newOrder.getId());
            newClientOrderItemEntity.setAdditionalComments(orderItemList.getAdditionalComments());
            newClientOrderItemEntity.setItemMenuId(orderItemList.getItemMenuId());
            newClientOrderItemEntity.setItemName(orderItemList.getItemName());
            newClientOrderItemEntity.setDescription(orderItemList.getDescription());
            newClientOrderItemEntity.setQuantity(orderItemList.getQuantity());
            clientOrderItemEntityList.add(newClientOrderItemEntity);
        }
        clientOrderItemRepository.saveAll(clientOrderItemEntityList);
    }

    private static long orderIdRandom() {
        Random random = new Random();
        int min = 00001;
        int max = 99999;
        return random.nextInt(max - min + 1) + min;
    }
}
