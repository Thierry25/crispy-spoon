package com.thierry.webservices.restfulwebservices.repositories;

import com.thierry.webservices.restfulwebservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
