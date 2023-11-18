package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.CreateQrDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.dto.response.QrResponse;
import com.project.menuflash.entity.QrEntity;
import com.project.menuflash.entity.StateEntity;

import java.util.Optional;

public class StateMapper {

    public static FindAllStateResponse entityToResponse(StateEntity stateEntity){
        FindAllStateResponse state = new FindAllStateResponse();
        state.setId(stateEntity.getId());
        state.setName(stateEntity.getName());
        return state;
    }

    public static StateEntity updateEntityFromDto(UpdateStateDto dto, StateEntity stateEntity) {
        stateEntity.setId(dto.getId());
        stateEntity.setName(Optional.ofNullable(dto.getName()).orElse(stateEntity.getName()));
        return stateEntity;
    }

}
