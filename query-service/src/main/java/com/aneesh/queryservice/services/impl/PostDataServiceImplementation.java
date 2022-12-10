package com.aneesh.queryservice.services.impl;

import com.aneesh.queryservice.entities.PostData;
import com.aneesh.queryservice.exceptions.ResourceNotFoundException;
import com.aneesh.queryservice.repositories.IPostDataRepository;
import com.aneesh.queryservice.services.IPostDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDataServiceImplementation implements IPostDataServices {

    @Autowired
    private IPostDataRepository postDataRepository;

    @Override
    public List<PostData> getAllPosts() {
        List<PostData> posts = postDataRepository.findAll();
        if(posts.isEmpty()){
            throw new ResourceNotFoundException("Posts not found");
        }

        return posts;
    }

    @Override
    public PostData getPost(long postId) {
        PostData post = postDataRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found"));

        return post;
    }
}
