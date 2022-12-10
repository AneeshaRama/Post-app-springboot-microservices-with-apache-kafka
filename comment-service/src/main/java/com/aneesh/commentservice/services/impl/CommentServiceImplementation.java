package com.aneesh.commentservice.services.impl;

import com.aneesh.basedomains.entities.CommentEvent;
import com.aneesh.commentservice.entites.Comment;
import com.aneesh.commentservice.exceptions.ResourceNotFoundException;
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

        CommentEvent commentEvent = new CommentEvent();

        commentEvent.setMessage("commentCreatedEvent");
        commentEvent.setCommentId(comment.getCommentId());
        commentEvent.setCommentContent(comment.getCommentContent());
        commentEvent.setPostId(comment.getPostId());

        kafkaProducer.sendCommentCreatedMessage(commentEvent);

        return newComment;
    }

    @Override
    public Comment updateComment(long commentId, Comment comment) throws JsonProcessingException {
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment not found"));

        existingComment.setCommentContent(comment.getCommentContent());

        commentRepository.save(existingComment);

        CommentEvent commentEvent = new CommentEvent();

        commentEvent.setMessage("commentUpdatedEvent");
        commentEvent.setCommentId(comment.getCommentId());
        commentEvent.setCommentContent(comment.getCommentContent());
        commentEvent.setPostId(comment.getPostId());

        kafkaProducer.sendCommentUpdatedMessage(commentEvent);

        return existingComment;
    }

    @Override
    public void deleteComment(long commentId) throws JsonProcessingException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment not found"));

        CommentEvent commentEvent = new CommentEvent();

        commentEvent.setMessage("commentDeletedEvent");
        commentEvent.setCommentId(comment.getCommentId());
        commentEvent.setCommentContent(comment.getCommentContent());
        commentEvent.setPostId(comment.getPostId());

        kafkaProducer.sendCommentDeletedMessage(commentEvent);

        commentRepository.delete(comment);

    }
}
