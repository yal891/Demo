package com.sample.demo.Service;

import com.sample.demo.Entity.User;

import java.util.List;

public interface UserService {

    public boolean create(User user);
    public boolean delete(Integer id);

    public User get(Integer id);

    public User update(Integer userId, User user);

    User saveUser(User user);

    User findByUsername(String username);

    List<User> findAll();


    public void clear();

    public User findOneById(Integer id);


//
//    public User findByUserName(String username);
//
//
//    public User getUserById(Integer id);
//
//    public User updateUser(User user);
//
//    public void deleteById(Integer id);
//
//    public User saveUser(User user);
//
//    public List<User> findAll();
//
//    User findByUsername(String name);
}
