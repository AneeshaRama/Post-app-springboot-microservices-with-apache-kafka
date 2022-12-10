package com.aneesh.postservice.kafka;

import com.aneesh.postservice.configs.KafkaConfig;
import com.aneesh.postservice.entities.Post;
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

    private KafkaTemplate<String, Post> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendPostCreatedMessage(Post post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);

        log.info(String.format("POST CREATED MESSAGE => %s", json));

        Message<Post> message = MessageBuilder
                .withPayload(post)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.postCreateTopic().name())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendPostUpdatedMessage(Post post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);

        log.info(String.format("POST UPDATED MESSAGE => %s", json));

        Message<Post> message = MessageBuilder
                .withPayload(post)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.postUpdateTopic().name())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendPostDeletedMessage(Post post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);

        log.info(String.format("POST DELETED MESSAGE => %s", json));

        Message<Post> message = MessageBuilder
                .withPayload(post)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.postDeleteTopic().name())
                .build();
        kafkaTemplate.send(message);
    }
}
