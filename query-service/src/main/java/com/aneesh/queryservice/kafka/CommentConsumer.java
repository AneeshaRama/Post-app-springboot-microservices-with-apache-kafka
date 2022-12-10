package com.aneesh.queryservice.kafka;

import com.aneesh.basedomains.entities.CommentEvent;
import com.aneesh.queryservice.entities.CommentData;
import com.aneesh.queryservice.entities.PostData;
import com.aneesh.queryservice.repositories.ICommentDataRepository;
import com.aneesh.queryservice.repositories.IPostDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentConsumer {

    private static final Logger log = LoggerFactory.getLogger(CommentConsumer.class);

    @Autowired
    private IPostDataRepository postDataRepository;

    @Autowired
    private ICommentDataRepository commentDataRepository;

    @KafkaListener(topics = "${comment.topic.name.create}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumeCommentCreatedEvent(CommentEvent commentEvent) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commentEvent);

        log.info(String.format("COMMENT CREATE EVENT RECEIVED => %s", json));

        CommentData commentData = new CommentData();

        commentData.setCommentId(commentEvent.getCommentId());
        commentData.setCommentContent(commentEvent.getCommentContent());

        commentDataRepository.save(commentData);

        PostData postData = postDataRepository.findById(commentEvent.getPostId()).get();

        postData.getComments().add(commentData);


        postDataRepository.save(postData);

        log.info("CommentData saved successfully...");

    }

    @KafkaListener(topics = "${comment.topic.name.update}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumeCommentUpdatedEvent(CommentEvent commentEvent) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commentEvent);

        log.info(String.format("COMMENT UPDATED EVENT RECEIVED => %s", json));

        CommentData commentData = commentDataRepository.findById(commentEvent.getCommentId()).get();

        commentData.setCommentContent(commentEvent.getCommentContent());

        commentDataRepository.save(commentData);

        log.info("CommentData updated successfully...");
    }

    @KafkaListener(topics = "${comment.topic.name.delete}", groupId = "${spring.kafka.consumer.group-id}")
    private void consumeCommentDeleteEvent(CommentEvent commentEvent) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commentEvent);

        log.info(String.format("COMMENT DELETE EVENT RECEIVED => %s", json));

        CommentData commentData = commentDataRepository.findById(commentEvent.getCommentId()).get();

        commentDataRepository.delete(commentData);

        log.info("CommentData deleted successfully...");
    }
}
