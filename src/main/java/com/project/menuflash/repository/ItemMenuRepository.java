package com.project.menuflash.repository;

import com.project.menuflash.entity.CategoryMenuEntity;
import com.project.menuflash.entity.ItemMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenuEntity, Long> {
}
