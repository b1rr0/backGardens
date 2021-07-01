package com.nure.backGardens.repository;


import com.nure.backGardens.entites.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

}
