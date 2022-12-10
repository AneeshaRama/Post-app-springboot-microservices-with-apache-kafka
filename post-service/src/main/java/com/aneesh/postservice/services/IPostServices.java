package com.aneesh.postservice.services;

import com.aneesh.postservice.entities.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPostServices {

    public Post createNewPost(Post post) throws JsonProcessingException;

    public Post updatePost(long postId, Post post);

    public String deletePost(long postId);

}
