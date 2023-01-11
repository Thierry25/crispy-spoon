package com.thierry.webservices.restfulwebservices.controllers;

import com.thierry.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.thierry.webservices.restfulwebservices.models.User;
import com.thierry.webservices.restfulwebservices.services.UserDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userDaoService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        User user = userDaoService.findUserById(id);
        if(user == null)
            throw new UserNotFoundException("id " + id);
        return user;
    }

    @PostMapping
    public ResponseEntity<User> saveNewUser(@RequestBody User user){
        User newUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
}
