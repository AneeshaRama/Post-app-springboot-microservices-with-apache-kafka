package com.aneesh.commentservice.controllers;

import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.services.ICommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentControllers {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/post/id/{postId}/new-comment")
    public ResponseEntity<?> createNewComment(@PathVariable(value="postId") long postId, @RequestBody Comment comment) throws JsonProcessingException {
        return new ResponseEntity<>(commentService.createNewComment(postId, comment), HttpStatus.CREATED);
    }

}
