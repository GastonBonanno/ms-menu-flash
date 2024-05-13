package com.project.menuflash.repository;

import com.project.menuflash.entity.ClientUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ClientUserEntity, Long> {
    ClientUserEntity findByEmail(String email);
}
