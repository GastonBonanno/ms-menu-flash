package com.project.menuflash.repository;

import com.project.menuflash.entity.CategoryMenuEntity;
import com.project.menuflash.entity.CompanyMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMenuRepository extends JpaRepository<CategoryMenuEntity, Long> {

//    List<CompanyMenuEntity> findByActiveAndId(Boolean aTrue);
}
