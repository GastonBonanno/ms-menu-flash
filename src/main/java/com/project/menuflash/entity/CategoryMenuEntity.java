package com.project.menuflash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.menuflash.dto.response.FindAllCategoryMenuResponse;
import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="category_menu")
@Data
public class CategoryMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_menu_id", insertable = false, updatable = false)
    private CompanyMenuEntity companyMenuEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryMenuEntity")
    private List<MenuItemEntity> menuItems;

    public FindAllCategoryMenuResponse toResponseDto(){
        FindAllCategoryMenuResponse categoryMenu = new FindAllCategoryMenuResponse();
        categoryMenu.setId(id);
        categoryMenu.setName(name);
        categoryMenu.setActive(active);
        categoryMenu.setMenuItems(menuItems.stream().map(MenuItemEntity::toResponseDto).collect(Collectors.toList()));
        return categoryMenu;
    }
}
