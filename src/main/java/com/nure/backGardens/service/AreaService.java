package com.nure.backGardens.service;



import com.nure.backGardens.Controllers.dtos.DtoArea;
import com.nure.backGardens.entites.AreaEntity;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.repository.AreaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaEntityRepository areaRes;

    public void delById(Integer id) {
        areaRes.deleteById(id);
    }

    public void addAreaToUser(DtoArea area, UserEntity userEntity) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setHeight(area.getHeight());
        areaEntity.setWidth(area.getWidth());
        areaEntity.setUser(userEntity);
        areaRes.save(areaEntity);
    }

}
