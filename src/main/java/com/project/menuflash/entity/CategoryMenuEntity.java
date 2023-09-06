package com.project.menuflash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonIgnore
    @OneToMany(mappedBy = "categoryMenuId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMenuEntity> itemsMenu;

    public List<ItemMenuEntity> getItemsMenu() {
        if(this.itemsMenu == null)
            this.itemsMenu = new ArrayList<>();
        return itemsMenu;
    }
}
