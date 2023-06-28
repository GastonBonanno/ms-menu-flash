package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="company_menu")
@Data
public class CompanyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private String description;
    private String header;
    private String footer;

    @Column(name = "client_user_id")
    private Long clientUserId;

    private Boolean active;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modificated_at")
    private Date modificatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "companyMenu")
    private List<CategoryMenu> categories;

}
