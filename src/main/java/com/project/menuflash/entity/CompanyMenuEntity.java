package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.menuflash.dto.response.FindAllCompanyMenuResponse;
import com.project.menuflash.dto.response.FindAllStateResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="company_menu")
@Data
public class CompanyMenuEntity {

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
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "companyMenuEntity")
    private List<CategoryMenuEntity> categories;

    public FindAllCompanyMenuResponse toResponseDto(){
        FindAllCompanyMenuResponse companyMenu = new FindAllCompanyMenuResponse();
        companyMenu.setId(id);
        companyMenu.setTitle(title);
        companyMenu.setDescription(description);
        companyMenu.setHeader(header);
        companyMenu.setFooter(footer);
        companyMenu.setClientUserId(clientUserId);
        companyMenu.setActive(active);
        companyMenu.setCreatedAt(createdAt);
        companyMenu.setModifiedAt(modifiedAt);
        companyMenu.setDeletedAt(deletedAt);
        companyMenu.setCategories(categories.stream().map(CategoryMenuEntity::toResponseDto).collect(Collectors.toList()));
        return companyMenu;
    }

}
