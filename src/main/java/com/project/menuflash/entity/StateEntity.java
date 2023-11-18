package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
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

    @OneToOne(mappedBy = "stateEntity")
    private ClientOrderEntity clientOrderEntity;


}
