package com.aneesh.commentservice.services.impl;

import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.kafka.KafkaProducer;
import com.aneesh.commentservice.repositories.ICommentRepository;
import com.aneesh.commentservice.services.ICommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public Comment createNewComment(long postId, Comment comment) throws JsonProcessingException {
        comment.setPostId(postId);
        Comment newComment = commentRepository.save(comment);

        kafkaProducer.sendCommentCreatedMessage(newComment);

        return newComment;
    }

    @Override
    public Comment updateComment(long commentId) {
        return null;
    }

    @Override
    public void deleteComment(long commentId) {

    }
}
