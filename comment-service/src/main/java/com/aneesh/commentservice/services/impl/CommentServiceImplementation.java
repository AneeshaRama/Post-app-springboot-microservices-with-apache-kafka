package com.aneesh.commentservice.services.impl;

import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.services.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements ICommentService {

    @Override
    public Comment createNewComment(long postId, Comment comment) {
        return null;
    }

    @Override
    public Comment updateComment(long commentId) {
        return null;
    }

    @Override
    public void deleteComment(long commentId) {

    }
}
