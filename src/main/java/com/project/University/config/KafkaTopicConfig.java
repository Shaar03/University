package com.project.University.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic universityTopic(){
        return TopicBuilder.name("university")
                .partitions(4)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "10000")
                .build();
    }
}
