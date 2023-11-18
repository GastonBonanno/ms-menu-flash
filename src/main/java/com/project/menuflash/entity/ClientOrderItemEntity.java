package com.project.menuflash.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="client_order_item")
@Data
public class ClientOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="additional_comments")
    private String additionalComments;

    @Column(name="item_menu_id")
    private Long itemMenuId;

    @Column(name="client_order_id")
    private Long clientOrderId;

    @Column(name="item_name")
    private String itemName;

    private String description;

    private Long quantity;
}
