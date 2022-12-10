package com.aneesh.postservice.controllers;

import com.aneesh.postservice.entities.Post;
import com.aneesh.postservice.services.IPostServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostControllers {

    @Autowired
    private IPostServices postServices;

    @PostMapping("/new-post")
    public ResponseEntity<?> createNewPost(@RequestBody Post post) throws JsonProcessingException {
        return new ResponseEntity<>(postServices.createNewPost(post), HttpStatus.CREATED);
    }
}
