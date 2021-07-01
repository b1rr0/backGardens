package com.nure.backGardens.repository;


import com.nure.backGardens.entites.IotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IotEntityRepository extends JpaRepository<IotEntity, Long> {

     List<IotEntity> findAllByAreaId(long areaId);

    List<IotEntity> findAllByArea(String user);
    void deleteById(long id);

}
