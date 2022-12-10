package com.aneesh.commentservice.services;

import com.aneesh.commentservice.entites.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICommentService {

    public Comment createNewComment(long postId, Comment comment) throws JsonProcessingException;

    public Comment updateComment(long commentId, Comment comment) throws JsonProcessingException;

    public void deleteComment(long commentId);
}
