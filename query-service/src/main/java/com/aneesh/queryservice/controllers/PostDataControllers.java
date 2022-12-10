package com.aneesh.queryservice.controllers;

import com.aneesh.queryservice.services.IPostDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostDataControllers {

    @Autowired
    private IPostDataServices postDataServices;

    @GetMapping("/all-posts")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postDataServices.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/post/id/{postId}")
    public ResponseEntity<?> getPost(@PathVariable(value ="postId") long postId){
        return new ResponseEntity<>(postDataServices.getPost(postId), HttpStatus.OK);
    }
}
