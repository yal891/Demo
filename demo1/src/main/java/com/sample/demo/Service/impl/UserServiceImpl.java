package com.sample.demo.Service.impl;

import com.sample.demo.Entity.User;
import com.sample.demo.Service.ProjectService;
import com.sample.demo.Service.UserService;
import com.sample.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    ProjectService projectService;

    @Override
    public User get(Integer id) {

        Optional<User> a= userRepository.findById(id);
        if(a.isPresent()){
            return a.get();
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        try{
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User update(Integer userId, User user) {
        User output = null;
        Optional<User> toUpdate = userRepository.findById(userId);

        if (toUpdate.isPresent()) {
            User updatedUser = toUpdate.get();
            updatedUser.setUserName(user.getUserName());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());

            try{
                output = userRepository.save(updatedUser);
                return output;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        return null;
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }

    @Override
    public User findOneById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public boolean delete(Integer id) {
        if(userRepository.findById(id)==null){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }





}
