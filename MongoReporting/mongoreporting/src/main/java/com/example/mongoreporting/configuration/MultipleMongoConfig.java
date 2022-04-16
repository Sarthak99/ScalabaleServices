package com.example.mongoreporting.configuration;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig{

    @Primary
    @Bean(name = "studentProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.studentdb")
    public MongoProperties getStudentProps() throws Exception {
        return new MongoProperties();
    }

    @Bean(name = "courseProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.coursedb")
    public MongoProperties getCourseProps() throws Exception {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "studentMongoTemplate")
    public MongoTemplate studentMongoTemplate() throws Exception {
        return new MongoTemplate(studentMongoDatabaseFactory(getStudentProps()));
    }

    @Bean(name ="courseMongoTemplate")
    public MongoTemplate courseMongoTemplate() throws Exception {
        return new MongoTemplate(courseMongoDatabaseFactory(getCourseProps()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory studentMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory courseMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }
}