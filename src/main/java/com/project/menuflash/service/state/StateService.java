package com.project.menuflash.service.state;

import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.GetStateResponseDto;

import java.util.List;

public interface StateService {
  List<GetStateResponseDto> getStates() throws Exception;

  GetStateResponseDto getStateById(Long id) throws Exception;

  void updateState(UpdateStateDto updateStateDto) throws Exception;

  void deleteState(Long id) throws Exception;

  void createState(CreateStateDto createStateDTO) throws Exception;
}
