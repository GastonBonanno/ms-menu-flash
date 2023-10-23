package com.project.menuflash.repository;

import com.project.menuflash.entity.CompanyDataEntity;
import com.project.menuflash.entity.QrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<QrEntity, Long> {

}
