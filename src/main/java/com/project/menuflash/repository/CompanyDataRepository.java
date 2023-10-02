package com.project.menuflash.repository;

import com.project.menuflash.entity.CompanyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDataRepository extends JpaRepository<CompanyDataEntity, Long> {

    CompanyDataEntity findByClientUserId(Long clientUserId) throws Exception;

}
