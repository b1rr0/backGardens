package com.nure.backGardens.Controllers;

import com.nure.backGardens.BusinnesLogick.GetBestWay;
import com.nure.backGardens.Controllers.dtos.DtoId;
import com.nure.backGardens.Controllers.dtos.DtoIot;
import com.nure.backGardens.Controllers.dtos.DtoMap;
import com.nure.backGardens.config.jwt.JwtProvider;
import com.nure.backGardens.entites.AreaEntity;
import com.nure.backGardens.entites.IotEntity;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.service.AreaService;
import com.nure.backGardens.service.IotService;
import com.nure.backGardens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IotController {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;

    @Autowired
    private IotService iotService;

    @GetMapping("/user/getallIots")
    @ResponseBody
    public List<IotEntity> getIots(@RequestBody DtoId id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        return iotService.findAllByUserAndArea(id.getId(), user);
    }


    @PostMapping("/user/getallIots")
    @ResponseBody
    public List<IotEntity> getItots(@RequestBody DtoId id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        return iotService.findAllByUserAndArea(id.getId(), user);
    }


    @GetMapping("/user/getIot")
    @ResponseBody
    public List<DtoMap> getItot(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        List<DtoMap> list = new ArrayList<DtoMap>() {
        };
        for (AreaEntity area : user.getAreas()) {
            DtoMap dtoMap=new DtoMap();
            dtoMap.setData(area.getId());
            dtoMap.setCount( area.getIotEntities().size());
            list.add(dtoMap);
        }
        return list;
    }

    @PostMapping(value = "/user/addIot")
    public HttpStatus addIot(@RequestBody DtoIot iot, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        iotService.addIot(iot, user);
        return HttpStatus.OK;
    }

    @GetMapping("/user/getBestWay")
    @ResponseBody
    public List<Long> getBestWay(@RequestBody DtoId id, HttpServletRequest request) throws IOException {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        List<IotEntity> tmp = iotService.findAllByUserAndArea(id.getId(), user);
        tmp = GetBestWay.getBestWay((ArrayList<IotEntity>) tmp);
        List<Long> res = new ArrayList<>();
        for (IotEntity iotEntity : tmp) {
            res.add(iotEntity.getId());
        }
        return res;
    }
}
