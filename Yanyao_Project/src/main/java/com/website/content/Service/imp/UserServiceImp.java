package com.website.content.Service.imp;

import com.website.content.DAO.UserDAO;
import com.website.content.entity.User;
import com.website.content.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findOneById(Integer Id) {
        return userDAO.findById(Id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

  
    @Override
    public void deleteById(Integer Id){
        userDAO.deleteById(Id);
    }

    @Override
    public User findUserByUsername(String name){return userDAO.findUserByUsername(name);}

//    @Override
//    public User updateUser(Integer Id){
//        User newUser = userDAO.findById(Id);
//        if (newUser != null){
//
//        }
//
//    };


}
