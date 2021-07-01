package com.nure.backGardens.service;


import com.nure.backGardens.entites.RoleEntity;
import com.nure.backGardens.entites.UserEntity;
import com.nure.backGardens.repository.RoleEntityRepository;
import com.nure.backGardens.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        System.out.println(userEntity.getPassword());
        return userEntityRepository.save(userEntity);
    }

    public UserEntity saveAdmin(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_ADMIN");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        System.out.println(userEntity.getPassword());
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public void delById(Integer id) {
        userEntityRepository.deleteById(id);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
