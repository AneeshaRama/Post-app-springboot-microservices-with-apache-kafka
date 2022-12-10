package com.aneesh.queryservice.controllers;

import com.aneesh.queryservice.services.IPostDataServices;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info = @Info(title = "Post API", description = "Get All Posts and Get Info of single post"))
public class PostDataControllers {

    @Autowired
    private IPostDataServices postDataServices;

    @GetMapping("/all-posts")
    @Operation(summary = "Get all Posts")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postDataServices.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/post/id/{postId}")
    @Operation(summary = "Get information of single post")
    public ResponseEntity<?> getPost(@PathVariable(value ="postId") long postId){
        return new ResponseEntity<>(postDataServices.getPost(postId), HttpStatus.OK);
    }
}
