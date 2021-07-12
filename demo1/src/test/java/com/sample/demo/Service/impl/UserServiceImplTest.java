package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Role;
import com.sample.demo.Entity.User;
import com.sample.demo.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;
    @Test
    void saveUser() {
        User user = new User();
        user.setUserName("yanyao");
        user.setPassword("123456");
        service.saveUser(user);
        System.out.println(user);
    }


}