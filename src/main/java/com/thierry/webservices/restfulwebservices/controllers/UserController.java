package com.thierry.webservices.restfulwebservices.controllers;

import com.thierry.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.thierry.webservices.restfulwebservices.models.Post;
import com.thierry.webservices.restfulwebservices.models.User;
import com.thierry.webservices.restfulwebservices.services.PostDaoService;
import com.thierry.webservices.restfulwebservices.services.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final PostDaoService postDaoService;

    public UserController(UserDaoService userDaoService, PostDaoService postDaoService){
        this.userDaoService = userDaoService;
        this.postDaoService = postDaoService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userDaoService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
        User user = userDaoService.findUserById(id);
        if(user == null)
            throw new UserNotFoundException("id " + id);
        var entityModel = EntityModel.of(user);
        var mvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                this.getClass()).getAllUsers());
        entityModel.add(mvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userDaoService.deleteUser(id);
    }

    @PostMapping
    public ResponseEntity<User> saveNewUser(@Valid @RequestBody User user){
        User newUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getCurrentUserPosts(@PathVariable Integer id){
        User user = userDaoService.findUserById(id);
        if(user == null)
            throw new UserNotFoundException("id " + id);
        return user.getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post){
        User user = userDaoService.findUserById(id);
        if(user == null)
            throw new UserNotFoundException("id " + id);
        Post savedPost = postDaoService.save(user, post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
