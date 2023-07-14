package com.project.menuflash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category_menu")
@Data
public class CategoryMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;

    @Column(name = "company_menu_id")
    private Long companyMenuId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_menu_id")
//    private CompanyMenuEntity companyMenuEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryMenuId")
    private List<ItemMenuEntity> itemsMenu;

}
