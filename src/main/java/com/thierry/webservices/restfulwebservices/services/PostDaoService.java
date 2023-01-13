package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.Post;
import com.thierry.webservices.restfulwebservices.models.User;
import org.springframework.stereotype.Service;

@Service
public class PostDaoService{

    private final PostDao postDao;

    public PostDaoService(PostDao postDao){
        this.postDao = postDao;
    }

    public Post save(User user, Post post){
        return postDao.savePost(user, post);
    }
}
