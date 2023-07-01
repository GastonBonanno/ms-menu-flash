package com.project.menuflash.repository;

import com.project.menuflash.entity.CompanyMenuEntity;
import com.project.menuflash.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMenuRepository extends JpaRepository<CompanyMenuEntity, Long> {
}
