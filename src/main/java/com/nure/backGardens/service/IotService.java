package com.nure.backGardens.service;


import com.nure.backGardens.Controllers.dtos.DtoIot;


import com.nure.backGardens.entites.AreaEntity;
import com.nure.backGardens.entites.IotEntity;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.repository.AreaEntityRepository;
import com.nure.backGardens.repository.IotEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IotService {

    @Autowired
    private IotEntityRepository iotRep;
    @Autowired
    private AreaEntityRepository areaRep;


    public void addIot(DtoIot iot, UserEntity user) {
        AreaEntity ar = areaRep.findById(iot.getIdArea());
        if (!ar.getUser().equals(user)) return;

        IotEntity iotEntity = new IotEntity();
        iotEntity.setxCoordinate(iot.getxCoordinate());
        iotEntity.setyCoordinate(iot.getyCoordinate());
        iotEntity.setDescription(iot.getDescription());
        iotEntity.setMaxHumidity(iot.getMaxHumidity());
        iotEntity.setMinHumidity(iot.getMinHumidity());
        iotEntity.setMaxTmp(iot.getMaxTmp());
        iotEntity.setMinTmp(iot.getMinTmp());
        iotEntity.setArea(ar);
        iotRep.save(iotEntity);
    }
    public void delById(Integer id) {
        iotRep.deleteById(id);
    }
    public List<IotEntity> findAllByUserAndArea(int id, UserEntity user) {
        AreaEntity ar = areaRep.findById(id);
        if (!ar.getUser().equals(user)) return null;
        return iotRep.findAllByAreaId(id);
    }



}
