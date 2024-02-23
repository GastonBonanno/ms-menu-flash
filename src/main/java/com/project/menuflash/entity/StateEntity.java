package com.project.menuflash.entity;


import jakarta.persistence.*;
import lombok.Data;


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
