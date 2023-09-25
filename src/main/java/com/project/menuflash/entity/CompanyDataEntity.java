package com.project.menuflash.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="company_data")
@Data
public class CompanyDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private Long cuit;
    private String address;

    @Column(name="phone_number")
    private Long phoneNumber;

    private boolean active;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="modified_at")
    private Date modifiedAt;

    @Column(name="deleted_at")
    private Date deletedAt;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_user_id", referencedColumnName = "id")
    private ClientUserEntity clientUserEntity;

    @Override
    public String toString() {
        return "CompanyDataEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cuit='" + cuit + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
