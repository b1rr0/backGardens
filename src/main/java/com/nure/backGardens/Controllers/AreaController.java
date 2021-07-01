package com.nure.backGardens.Controllers;

import com.nure.backGardens.Controllers.dtos.DtoArea;
import com.nure.backGardens.config.jwt.JwtProvider;
import com.nure.backGardens.entites.AreaEntity;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.service.AreaService;
import com.nure.backGardens.service.IotService;
import com.nure.backGardens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class AreaController {


    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private IotService iotService;




    @GetMapping("/user/getallAreas")
    @ResponseBody
    public List<AreaEntity> getAreas(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserEntity user = userService.findByLogin(jwtProvider.getLoginFromToken(token.substring(7)));
        return user.getAreas();
    }


}
