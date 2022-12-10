package com.aneesh.queryservice.kafka;

import com.aneesh.basedomains.entities.PostEvent;
import com.aneesh.queryservice.entities.PostData;
import com.aneesh.queryservice.repositories.IPostDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {

    private static final Logger log = LoggerFactory.getLogger(PostConsumer.class);

    @Autowired
    private IPostDataRepository postDataRepository;

    @KafkaListener(topics = "postCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    private void consumePostCreatedEvent(PostEvent postEvent) throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(postEvent);

        log.info(String.format("POST CREATE EVENT RECEIVED => %s", json));

        PostData postData = new PostData();

        postData.setPostId(postEvent.getPostId());
        postData.setPostTitle(postEvent.getPostTitle());
        postData.setPostContent(postEvent.getPostContent());
        System.out.println(postData.getPostId());
        postDataRepository.save(postData);
    }
}
