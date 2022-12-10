package com.aneesh.commentservice.controllers;

import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.services.ICommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentControllers {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/post/id/{postId}/new-comment")
    public ResponseEntity<?> createNewComment(@PathVariable(value="postId") long postId, @RequestBody Comment comment) throws JsonProcessingException {
        return new ResponseEntity<>(commentService.createNewComment(postId, comment), HttpStatus.CREATED);
    }

    @PutMapping("/comment/id/{commentId}/update-comment")
    public ResponseEntity<?> updateComment(@PathVariable(value = "commentId") long commentId, @RequestBody Comment comment) throws JsonProcessingException {
        return new ResponseEntity<>(commentService.updateComment(commentId, comment), HttpStatus.OK);
    }

}
