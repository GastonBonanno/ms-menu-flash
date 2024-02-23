package com.project.menuflash.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="client_user")
@Data
public class ClientUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String email;
    private String password;
    private Boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @OneToOne(mappedBy = "clientUserEntity")
    private CompanyDataEntity companyDataEntity;

}
