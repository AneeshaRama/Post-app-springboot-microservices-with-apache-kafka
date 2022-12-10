package com.aneesh.postservice.services.impl;

import com.aneesh.postservice.entities.Post;
import com.aneesh.postservice.services.IPostServices;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImplementation implements IPostServices {

    @Override
    public Post createNewPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(long postId, Post post) {
        return null;
    }

    @Override
    public String deletePost(long postId) {
        return null;
    }
}
