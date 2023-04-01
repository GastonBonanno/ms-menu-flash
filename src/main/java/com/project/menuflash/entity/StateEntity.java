package com.project.menuflash.entity;


import com.project.menuflash.domain.StateDomain;
import com.project.menuflash.dto.StateDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;


@Entity
@Table(name="state")
@Data
public class StateEntity {
    @Id
    private  Long id;
    private String name;

    public StateDomain toDomain(){
        StateDomain state = new StateDomain();
        state.setId(id);
        state.setName(name);
        return state;
    }

    public StateEntity updateFromDto(StateDTO dto) {
        setId(dto.getId());
        setName(Optional.ofNullable(dto.getName()).orElse(getName()));
        return this;
    }

}
