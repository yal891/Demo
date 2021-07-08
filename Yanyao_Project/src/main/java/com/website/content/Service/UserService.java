package com.website.content.Service;

import com.website.content.entity.User;

import java.util.List;

public interface UserService {
    public User findOneById(Integer Id);

    List<User> findAll();

    public User findUserByUsername(String name);

    public User save(User user);

    public void deleteById(Integer Id);

//    public User updateUser(Integer Id);


}
