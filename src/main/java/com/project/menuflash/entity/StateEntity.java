package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name="state")
@Data
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ClientOrderEntity> clientOrdersEntity;

}
