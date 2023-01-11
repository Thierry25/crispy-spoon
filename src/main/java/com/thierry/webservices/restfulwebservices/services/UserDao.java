package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    User save(User user);
    Optional<User> findUserById(int id);
    void deleteUserById(int id);
}
