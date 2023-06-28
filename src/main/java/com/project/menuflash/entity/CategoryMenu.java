package com.project.menuflash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category_menu")
@Data
public class CategoryMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_menu_id", insertable = false, updatable = false)
    private CompanyMenu companyMenu;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryMenu")
    private List<MenuItem> menuItems;
}
