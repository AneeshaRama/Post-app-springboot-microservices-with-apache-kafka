package com.aneesh.postservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${post.topic.name.create}")
    private String postCreatedTopicName;

    @Value("${post.topic.name.update}")
    private String postUpdatedTopicName;

    @Value("${post.topic.name.delete}")
    private String postDeletedTopicName;



    public NewTopic postCreateTopic(){

        return TopicBuilder.name(postCreatedTopicName).build();
    }


    public NewTopic postUpdateTopic(){

        return TopicBuilder.name(postUpdatedTopicName).build();
    }


    public NewTopic postDeleteTopic(){

        return TopicBuilder.name(postDeletedTopicName).build();
    }

}
