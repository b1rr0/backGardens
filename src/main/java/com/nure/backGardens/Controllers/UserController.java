package com.nure.backGardens.Controllers;

import com.nure.backGardens.BusinnesLogick.GetBestWay;
import com.nure.backGardens.Controllers.dtos.*;
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
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private IotService iotService;

    @PostMapping("/user/dellIot")
    public HttpStatus dellIt(@RequestBody DtoId id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        List<IotEntity> l = iotService.findAllByUserAndArea(id.getId(), user);
        boolean res = false;
        for (IotEntity iotEntity : l) {
            if (iotEntity.getId()==(id.getId())) {
                res = true;
            }
        }
        if (res){
            iotService.delById(id.getId());
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/user/dellArea")
    public HttpStatus dellArea(@RequestBody DtoId id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        List<AreaEntity> l = user.getAreas();
        boolean res = false;
        for (AreaEntity iotEntity : l) {
            if (iotEntity.getId()==(id.getId())) {
                res = true;
            }
        }
        if (res){
            areaService.delById(id.getId());
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }



    @PostMapping("/register")
    public HttpStatus registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        System.out.println(u.toString());
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        System.out.println(u.getPassword());
        userService.saveUser(u);
        return HttpStatus.OK;
    }

    @PostMapping("/login")
    public DtoToken auth(@RequestBody RegistrationRequest request) {
        System.out.println(request.getLogin());
        System.out.println(request.getPassword());
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        System.out.println(userEntity.getId());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new DtoToken(token);

    }
    @PostMapping(value = "/user/addArea")
    public HttpStatus addArea(@RequestBody DtoArea area, HttpServletRequest request) {
        System.out.println(request.toString());
        System.out.println(request.toString());
        System.out.println(request.toString());
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        areaService.addAreaToUser(area, user);
        return HttpStatus.OK;
    }

}
