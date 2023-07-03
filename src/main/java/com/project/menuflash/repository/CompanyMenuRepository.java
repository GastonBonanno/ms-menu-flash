package com.project.menuflash.repository;

import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMenuRepository extends JpaRepository<CompanyMenuEntity, Long> {

    List<CompanyMenuEntity> findByActive(Boolean aTrue);
}
