package com.aneesh.postservice.services;

import com.aneesh.postservice.entities.Post;

public interface IPostServices {

    public Post createNewPost(Post post);

    public Post updatePost(long postId, Post post);

    public String deletePost(long postId);

}
