package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="client_order")
@Data
public class ClientOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_id")
    private Long orderId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private StateEntity stateEntity;

    @Column(name = "state_id", insertable=false, updatable=false)
    private Long stateId;

    @Column(name="table_name")
    private String tableName;

    @Column(name="company_menu_id")
    private Long companyMenuId;

    private Boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "clientOrderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientOrderItemEntity> clientOrderItemEntityList;
}
