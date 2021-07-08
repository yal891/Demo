package com.website.content.controller;

import com.website.content.Service.imp.MyUserDetailsService;
import com.website.content.entity.AuthenticationResponse;

import com.website.content.entity.User;
import com.website.content.Service.UserService;
import com.website.content.util.JwtUtil;
import com.website.content.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//
//@Controller
//public class UserController {
//
//    @GetMapping("/authentication/login")
//    public String authenticationLogin() throws IOException {
//        return "login";
//    }
//}

@RestController
//@RequestMapping("User")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Qualifier ("userServiceImp")
    @Autowired
    private UserService service;

    @GetMapping("/Users")
    public List<User> list() {
        return service.findAll();
    }

    @PostMapping("/register")
    public User create(@RequestBody User testuser) {

        return service.save(testuser);
    }

    @PostMapping("/UpdateUser")
    public User update(@RequestBody User user){
        return service.save(user);
    }

    @PostMapping("/deleteUser/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.deleteById(id);
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {return service.findOneById(id);}

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User TestUser) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(TestUser.getUserName(), TestUser.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(TestUser.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}