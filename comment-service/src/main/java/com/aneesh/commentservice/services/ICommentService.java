package com.aneesh.commentservice.services;

import com.aneesh.commentservice.entites.Comment;

public interface ICommentService {

    public Comment createNewComment(long postId, Comment comment);

    public Comment updateComment(long commentId);

    public void deleteComment(long commentId);
}
