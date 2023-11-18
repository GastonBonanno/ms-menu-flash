package com.project.menuflash.service.state;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.entity.StateEntity;
import com.project.menuflash.mapper.StateMapper;
import com.project.menuflash.repository.StateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService{

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);
    private final StateRepository stateRepository;


    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<FindAllStateResponse> getStates() throws ResponseStatusException {
        try {
            List<StateEntity> stateEntities = stateRepository.findAll();
            return stateEntities.stream().map(StateMapper::entityToResponse).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("getStates error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar estados", e);
        }
    }

    public FindAllStateResponse getStateById(Long id) throws ResponseStatusException {
        try {
            return StateMapper.entityToResponse(getStateEntityById(id));
        } catch (Exception e) {
            LOG.error("getStateById error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar estado", e);
        }
    }

    public void createState(CreateStateDto createStateDTO) throws ResponseStatusException {
        try {
            StateEntity stateEntity = new StateEntity();
            stateEntity.setName(createStateDTO.getName());
            stateRepository.save(stateEntity);
        } catch (Exception e) {
            LOG.error("getStates error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear estado", e);
        }
    }

    public void updateState(UpdateStateDto updateStateDto,Long id) throws ResponseStatusException {
        try {
            StateEntity stateEntity = getStateEntityById(id);
            stateRepository.save(StateMapper.updateEntityFromDto(updateStateDto, stateEntity));
        } catch (Exception e) {
            LOG.error("updateState error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar estado", e);
        }
    }

    public void deleteState(Long id) throws ResponseStatusException {
        try {
            stateRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("deleteState error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar estado", e);
        }
    }

    private StateEntity getStateEntityById(Long id) throws Exception {
        try {
            return stateRepository.findById(id).orElse(null);
        } catch (Exception e) {
            LOG.error("getStateEntityById error: {}", e.getMessage());
            throw new Exception();
        }
    }
}
