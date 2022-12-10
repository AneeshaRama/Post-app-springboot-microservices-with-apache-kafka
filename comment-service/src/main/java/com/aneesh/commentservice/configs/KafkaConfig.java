package com.aneesh.commentservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${comment.topic.name.create}")
    private String commentCreatedTopicName;

    @Value("${comment.topic.name.update}")
    private String commentUpdatedTopicName;

    @Value("${comment.topic.name.delete}")
    private String commentDeletedTopicName;



    public NewTopic commentCreateTopic(){

        return TopicBuilder.name(commentCreatedTopicName).build();
    }


    public NewTopic commentUpdateTopic(){

        return TopicBuilder.name(commentUpdatedTopicName).build();
    }


    public NewTopic commentDeleteTopic(){

        return TopicBuilder.name(commentDeletedTopicName).build();
    }

}