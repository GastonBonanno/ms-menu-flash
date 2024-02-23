package com.project.menuflash.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="item_menu")
@Data
public class ItemMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long position;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @Column(name = "category_menu_id")
    private Long categoryMenuId;

}
