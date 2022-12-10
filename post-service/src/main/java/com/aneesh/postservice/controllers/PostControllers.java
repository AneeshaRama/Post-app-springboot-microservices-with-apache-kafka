package com.aneesh.postservice.controllers;

import com.aneesh.postservice.entities.Post;
import com.aneesh.postservice.services.IPostServices;
import com.aneesh.postservice.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@OpenAPIDefinition(info = @Info(title = "Post API", description = "Create, Update and Delete Post"))
public class PostControllers {

    @Autowired
    private IPostServices postServices;

    @PostMapping("/new-post")
    @Operation(summary = "To create new post")
    public ResponseEntity<?> createNewPost(@RequestBody Post post) throws JsonProcessingException {
        return new ResponseEntity<>(postServices.createNewPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/post/id/{postId}/update-post")
    @Operation(summary = "To update the post")
    public ResponseEntity<?> updatePost(@PathVariable(value = "postId") long postId, @RequestBody Post post) throws JsonProcessingException {
        return new ResponseEntity<>(postServices.updatePost(postId, post), HttpStatus.OK);
    }

    @DeleteMapping("/post/id/{postId}/delete-post")
    @Operation(summary = "To delete the post")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable(value = "postId") long postId) throws JsonProcessingException {
        postServices.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post has been deleted successfully", true) , HttpStatus.OK);
    }
}
