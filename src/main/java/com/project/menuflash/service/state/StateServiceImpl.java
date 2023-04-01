package com.project.menuflash.service.state;

import com.project.menuflash.domain.StateDomain;
import com.project.menuflash.dto.StateDTO;
import com.project.menuflash.entity.StateEntity;
import com.project.menuflash.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService{
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateDomain> getStates() throws Exception {
        List<StateEntity> stateEntities = stateRepository.findAll();
        return stateEntities.stream().map(StateEntity::toDomain).collect(Collectors.toList());
    }

    public StateDomain getStateById(Long id) throws Exception {
        StateEntity stateIdEntity = stateRepository.findById(id).get();
        return stateIdEntity.toDomain();
    }

    public void updateState(StateDTO stateDto) throws Exception {
        try {
            StateEntity stateEntity = getStateEntityById(stateDto.getId());
            stateRepository.save(stateEntity.updateFromDto(stateDto));
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
