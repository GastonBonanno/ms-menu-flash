package com.project.menuflash.entity;


import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="menu_item")
@Data
public class ItemMenuEntity {

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
    @JoinColumn(name = "category_menu_id")
    private CategoryMenuEntity categoryMenuEntity;

}
