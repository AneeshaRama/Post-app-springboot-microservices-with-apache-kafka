package com.aneesh.postservice.services;

import com.aneesh.postservice.entities.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPostServices {

    public Post createNewPost(Post post) throws JsonProcessingException;

    public Post updatePost(long postId, Post post) throws JsonProcessingException;

    public void deletePost(long postId) throws JsonProcessingException;

}
