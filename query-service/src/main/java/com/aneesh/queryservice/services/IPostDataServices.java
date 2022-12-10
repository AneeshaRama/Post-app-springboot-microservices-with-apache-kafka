package com.aneesh.queryservice.services;

import com.aneesh.queryservice.entities.PostData;

import java.util.List;

public interface IPostDataServices {

    public List<PostData> getAllPosts();

    public PostData getPost(long postId);

}
