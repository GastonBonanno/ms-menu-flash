package com.project.menuflash.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="menu_item")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modificated_at")
    private Date modificatedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_menu_id", insertable = false, updatable = false)
    private CategoryMenu categoryMenu;
}
