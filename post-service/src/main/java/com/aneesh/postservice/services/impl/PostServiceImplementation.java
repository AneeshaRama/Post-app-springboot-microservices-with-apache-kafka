package com.aneesh.postservice.services.impl;

import com.aneesh.basedomains.entities.PostEvent;
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
        PostEvent postEvent = new PostEvent();

        postEvent.setMessage("postCreateEvent");
        postEvent.setPostId(newPost.getPostId());
        postEvent.setPostTitle(newPost.getPostTitle());
        postEvent.setPostContent(newPost.getPostContent());
        kafkaProducer.sendPostCreatedMessage(postEvent);
        return newPost;
    }

    @Override
    public Post updatePost(long postId, Post post) throws JsonProcessingException {
        Post existingPost = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found"));

        existingPost.setPostTitle(post.getPostTitle());
        existingPost.setPostContent(post.getPostContent());

        postRepository.save(existingPost);

        PostEvent postEvent = new PostEvent();

        postEvent.setMessage("postUpdateEvent");
        postEvent.setPostId(existingPost.getPostId());
        postEvent.setPostTitle(existingPost.getPostTitle());
        postEvent.setPostContent(existingPost.getPostContent());

        kafkaProducer.sendPostUpdatedMessage(postEvent);

        return existingPost;
    }

    @Override
    public void deletePost(long postId) throws JsonProcessingException {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found"));

        PostEvent postEvent = new PostEvent();
        postEvent.setMessage("postDeleteEvent");
        postEvent.setPostId(post.getPostId());
        postEvent.setPostTitle(post.getPostTitle());
        postEvent.setPostContent(post.getPostContent());
        kafkaProducer.sendPostDeletedMessage(postEvent);

        postRepository.delete(post);
    }
}
