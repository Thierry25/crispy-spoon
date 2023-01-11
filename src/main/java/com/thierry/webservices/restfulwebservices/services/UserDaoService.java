package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {

    private final UserDao userDao;

    public UserDaoService(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public User findUserById(int id){
        Optional<User> user =  userDao.findUserById(id);
        return user.orElse(null);
    }

    public User save (User user){
        return userDao.save(user);
    }
    // Can have other business logic in here
}
