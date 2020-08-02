package com.kafkaExample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local") //Configuration invoked only for local profile and not available for prod env
public class AutoConfig {

    @Bean
    public NewTopic employeeEvents() {
        return TopicBuilder.name("Employee-event").partitions(3).replicas(3).build();
    }
}
