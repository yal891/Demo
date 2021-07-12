package com.sample.demo.Controller;

import com.sample.demo.Entity.User;
import com.sample.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

//CRUD for User Controller
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Integer id){
//        if (service.findOneById(id) == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
//        }
        return service.findOneById(id);
    }

    @GetMapping ("/users/all")
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.FOUND);
    }

    @PostMapping("/users/create")
    public void createUser(@RequestBody User user){
        service.saveUser(user);
    }


    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") int id){
        service.delete(id);
    }


    @PutMapping("/users/{id}}")
    @Transactional
    public ResponseEntity<?> userUpdate(@PathVariable Integer id, @RequestBody User user) {
        if (service.get(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
        }
        return new ResponseEntity<>(service.update(id, user), HttpStatus.ACCEPTED);
    }

//    @GetMapping("/clear")
//    public ResponseEntity<?> clear() {
//        service.clear();
//        return ResponseEntity.status(HttpStatus.OK).body("All users deleted");
//    }
}
