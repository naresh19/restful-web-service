package com.naresh.firstservice.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@Controller
public class UserController {
    @Autowired
    UserDoaService service;

    @GetMapping(path = "/users")
    public List<User> getAll(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getOne(@PathVariable int id){
        User savedUser = service.findOne(id);
        if(savedUser == null)
            throw new UserNotFoundException("id - " + id);
        return savedUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        User deletedUser = service.deleteById(id);
        if(deletedUser == null)
            throw new UserNotFoundException("User not found while deleting " + id);
    }
}
