package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="company_menu")
@Data
public class CompanyMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branch;
    private String title;
    private String description;
    private String header;
    private String footer;
    private Boolean active;

    @Column(name = "company_data_id")
    private Long companyDataId;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "companyMenuId",cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<CategoryMenuEntity> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "companyMenuId",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QrEntity> qrs;

}
