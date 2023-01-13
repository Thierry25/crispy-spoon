package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.Post;
import com.thierry.webservices.restfulwebservices.models.User;

import java.util.List;

public interface PostDao {
    Post savePost(User user, Post post);
}
