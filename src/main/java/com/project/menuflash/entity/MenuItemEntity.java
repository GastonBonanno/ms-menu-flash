package com.project.menuflash.entity;

import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="menu_item")
@Data
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_menu_id", insertable = false, updatable = false)
    private CategoryMenuEntity categoryMenuEntity;

    public FindAllMenuItemResponse toResponseDto(){
        FindAllMenuItemResponse menuItem = new FindAllMenuItemResponse();
        menuItem.setId(id);
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setActive(active);
        menuItem.setCreatedAt(createdAt);
        menuItem.setModifiedAt(modifiedAt);
        menuItem.setDeletedAt(deletedAt);
        return menuItem;
    }
}
