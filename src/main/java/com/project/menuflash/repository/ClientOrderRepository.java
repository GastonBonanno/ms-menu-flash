package com.project.menuflash.repository;

import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ItemMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrderEntity, Long> {

    List<ClientOrderEntity> findByCompanyMenuIdAndActiveOrderByCreatedAtDesc(Long companyMenuId, boolean active);
    List<ClientOrderEntity> findByClientEmailAndActiveOrderByCreatedAtDesc(String clientEmail, boolean active);
}
