package com.nure.backGardens.repository;


import com.nure.backGardens.entites.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaEntityRepository extends JpaRepository<AreaEntity, Long> {

    List<AreaEntity> findAllByUserId(Integer id);
    AreaEntity findById(long str);
    void deleteById(long id);

}
