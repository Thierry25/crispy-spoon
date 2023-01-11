package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao{

    private static final List<User> users = new ArrayList<>();

    static{
        users.add(new User(1, "Thierry", LocalDate.now().minusYears(27)));
        users.add(new User(2, "Loudwige", LocalDate.now().minusYears(28)));
        users.add(new User(3, "Fabrice", LocalDate.now().minusYears(36)));
        users.add(new User(4, "Martine", LocalDate.now().minusYears(43)));
    }


    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User save(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
