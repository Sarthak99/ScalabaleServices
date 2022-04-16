package com.example.mongoreporting.data.courseRepositories;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course{

    @Id
    private String name;
    private String description;
    private String instructor;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

}
