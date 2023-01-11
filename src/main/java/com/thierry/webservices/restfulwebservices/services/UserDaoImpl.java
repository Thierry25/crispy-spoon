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

    private static int count = 0;

    static{
        users.add(new User(++count, "Thierry", LocalDate.now().minusYears(27)));
        users.add(new User(++count, "Loudwige", LocalDate.now().minusYears(28)));
        users.add(new User(++count, "Fabrice", LocalDate.now().minusYears(36)));
        users.add(new User(++count, "Martine", LocalDate.now().minusYears(43)));
    }


    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User save(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteUserById(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
