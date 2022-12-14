package com.aneesh.commentservice.controllers;

import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.services.ICommentService;
import com.aneesh.commentservice.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@OpenAPIDefinition(info = @Info(title = "Comment API", description = "Create, Update and Delete comment"))
public class CommentControllers {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/post/id/{postId}/new-comment")
    @Operation(summary = "To create new comment")
    public ResponseEntity<?> createNewComment(@PathVariable(value="postId") long postId, @RequestBody Comment comment) throws JsonProcessingException {
        return new ResponseEntity<>(commentService.createNewComment(postId, comment), HttpStatus.CREATED);
    }

    @PutMapping("/comment/id/{commentId}/update-comment")
    @Operation(summary = "To update comment")
    public ResponseEntity<?> updateComment(@PathVariable(value = "commentId") long commentId, @RequestBody Comment comment) throws JsonProcessingException {
        return new ResponseEntity<>(commentService.updateComment(commentId, comment), HttpStatus.OK);
    }

    @DeleteMapping("/comment/id/{commentId}/delete-comment")
    @Operation(summary = "To delete the comment")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(value = "commentId") long commentId) throws JsonProcessingException {
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment has been deleted successfully", true), HttpStatus.OK);
    }

}
