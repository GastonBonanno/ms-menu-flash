package com.project.menuflash.service.state;

import com.project.menuflash.domain.StateDomain;
import com.project.menuflash.dto.StateDTO;

import java.util.List;

public interface StateService {
  List<StateDomain> getStates() throws Exception;

  StateDomain getStateById(Long id) throws Exception;

  void updateState(StateDTO stateDto) throws Exception;

  void deleteState(Long id) throws Exception;
}
