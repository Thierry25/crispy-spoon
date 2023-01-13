package com.thierry.webservices.restfulwebservices.services;

import com.thierry.webservices.restfulwebservices.models.Post;
import com.thierry.webservices.restfulwebservices.models.User;
import com.thierry.webservices.restfulwebservices.repositories.PostRepository;
import org.springframework.stereotype.Component;


@Component
public class PostDaoImpl implements PostDao{

    private final PostRepository postRepository;

    public PostDaoImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(User user, Post post) {
        post.setUser(user);
        return postRepository.save(post);
    }
}
