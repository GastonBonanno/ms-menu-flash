package com.project.menuflash.repository;

import com.project.menuflash.entity.ClientOrderEntity;
import com.project.menuflash.entity.ClientOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderItemRepository extends JpaRepository<ClientOrderItemEntity, Long> {
}
