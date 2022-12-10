package com.aneesh.postservice.services.impl;

import com.aneesh.postservice.exceptions.ResourceNotFoundException;
import com.aneesh.postservice.entities.Post;
import com.aneesh.postservice.kafka.KafkaProducer;
import com.aneesh.postservice.repositories.IPostRepository;
import com.aneesh.postservice.services.IPostServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImplementation implements IPostServices {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public Post createNewPost(Post post) throws JsonProcessingException {
        Post newPost = postRepository.save(post);
        kafkaProducer.sendPostCreatedMessage(post);
        return newPost;
    }

    @Override
    public Post updatePost(long postId, Post post) throws JsonProcessingException {
        Post existingPost = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found"));

        existingPost.setPostTitle(post.getPostTitle());
        existingPost.setPostContent(post.getPostContent());

        postRepository.save(existingPost);

        kafkaProducer.sendPostUpdatedMessage(existingPost);

        return existingPost;
    }

    @Override
    public String deletePost(long postId) {
        return null;
    }
}
