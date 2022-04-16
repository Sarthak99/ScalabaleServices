package com.example.mongoreporting.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mongoreporting.data.courseRepositories"},
        mongoTemplateRef = CourseDBConfig.MONGO_TEMPLATE)
public class CourseDBConfig {
    protected static final String MONGO_TEMPLATE = "courseMongoTemplate";
}

