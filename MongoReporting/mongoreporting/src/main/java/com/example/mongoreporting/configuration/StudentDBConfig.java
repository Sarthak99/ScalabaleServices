package com.example.mongoreporting.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mongoreporting.data.studentRepositories"},
        mongoTemplateRef = StudentDBConfig.MONGO_TEMPLATE)
public class StudentDBConfig {
    protected static final String MONGO_TEMPLATE = "studentMongoTemplate";
}
    