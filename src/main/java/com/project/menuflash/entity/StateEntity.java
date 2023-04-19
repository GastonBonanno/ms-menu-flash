package com.project.menuflash.entity;


import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.CreateStateResponseDto;
import com.project.menuflash.dto.response.UpdateStateResponseDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;


@Entity
@Table(name="state")
@Data
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;

    public StateEntity(String name) {
        this.name = name;
    }
    public StateEntity() {

    }

    public UpdateStateResponseDto toResponseDto(){
        UpdateStateResponseDto state = new UpdateStateResponseDto();
        state.setId(id);
        state.setName(name);
        return state;
    }

    public StateEntity updateFromDto(UpdateStateDto dto) {
        setId(dto.getId());
        setName(Optional.ofNullable(dto.getName()).orElse(getName()));
        return this;
    }

}
