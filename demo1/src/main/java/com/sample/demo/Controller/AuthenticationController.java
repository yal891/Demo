package com.sample.demo.Controller;

import com.sample.demo.Entity.User;
import com.sample.demo.Service.UserService;
import com.sample.demo.Service.impl.MyUserDetailsService;
import com.sample.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/")
    public String home() {
        return "Anyone can see home page without logging in";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return  new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this username already exists!");
        }
//        user.setRole("ROLE_USER");
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

}

