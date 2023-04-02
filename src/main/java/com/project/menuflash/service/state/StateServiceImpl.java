package com.project.menuflash.service.state;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.UpdateStateResponseDto;
import com.project.menuflash.entity.StateEntity;
import com.project.menuflash.repository.StateRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService{

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);
    private final StateRepository stateRepository;


    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<UpdateStateResponseDto> getStates() throws Exception {
        List<StateEntity> stateEntities = stateRepository.findAll();
        return stateEntities.stream().map(StateEntity::toResponseDto).collect(Collectors.toList());
    }

    public UpdateStateResponseDto getStateById(Long id) throws Exception {
        StateEntity stateIdEntity = stateRepository.findById(id).get();
        return stateIdEntity.toResponseDto();
    }

    public void createState(CreateStateDto createStateDTO) throws Exception {
        try {
            StateEntity entity = new StateEntity();
            entity.setName(createStateDTO.getName());
            stateRepository.save(entity);
        } catch (Exception e) {
            LOG.error("Error: {}", e.getMessage());
            throw new Exception("Update entity error");
        }
    }

    public void updateState(UpdateStateDto updateStateDto) throws Exception {
        try {
            StateEntity stateEntity = getStateEntityById(updateStateDto.getId());
            stateRepository.save(stateEntity.updateFromDto(updateStateDto));
        } catch (Exception e) {
            throw new Exception("Update entity error");
        }
    }

    public void deleteState(Long id) throws Exception {
        stateRepository.deleteById(id);
    }

    private StateEntity getStateEntityById(Long id) throws Exception {
        return stateRepository.findById(id).get();
    }
}
