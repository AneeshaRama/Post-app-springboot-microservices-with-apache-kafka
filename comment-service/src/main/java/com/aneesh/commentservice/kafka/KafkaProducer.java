package com.aneesh.commentservice.kafka;

import com.aneesh.basedomains.entities.CommentEvent;
import com.aneesh.commentservice.configs.KafkaConfig;
import com.aneesh.commentservice.entites.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
@Setter
public class KafkaProducer {

    @Autowired
    private KafkaConfig kafkaConfig;

    private KafkaTemplate<String, CommentEvent> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendCommentCreatedMessage(CommentEvent comment) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(comment);

        log.info(String.format("COMMENT CREATED MESSAGE => %s", json));

        Message<CommentEvent> message = MessageBuilder
                .withPayload(comment)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.commentCreateTopic().name())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendCommentUpdatedMessage(CommentEvent comment) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(comment);

        log.info(String.format("COMMENT UPDATED MESSAGE => %s", json));

        Message<CommentEvent> message = MessageBuilder
                .withPayload(comment)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.commentUpdateTopic().name())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendCommentDeletedMessage(CommentEvent comment) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(comment);

        log.info(String.format("COMMENT DELETED MESSAGE => %s", json));

        Message<CommentEvent> message = MessageBuilder
                .withPayload(comment)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.commentDeleteTopic().name())
                .build();
        kafkaTemplate.send(message);
    }
}
