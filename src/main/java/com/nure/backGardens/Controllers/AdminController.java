package com.nure.backGardens.Controllers;

import com.nure.backGardens.Controllers.dtos.DtoId;
import com.nure.backGardens.Controllers.dtos.RegistrationRequest;
import com.nure.backGardens.config.jwt.JwtProvider;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;


    @PostMapping("/admin/register")
    public HttpStatus registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        System.out.println(u.toString());
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        System.out.println(u.getPassword());
        userService.saveAdmin(u);
        return HttpStatus.OK;
    }

    @PostMapping("/admin/delete")
    public HttpStatus deleteUser(@RequestBody DtoId id, @RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.delById(id.getId());
        return HttpStatus.OK;
    }

}
