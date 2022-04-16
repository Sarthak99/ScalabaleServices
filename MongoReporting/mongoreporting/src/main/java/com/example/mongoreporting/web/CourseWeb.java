package com.example.mongoreporting.web;

import com.example.mongoreporting.data.courseRepositories.Course;
import com.example.mongoreporting.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseWeb {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/course")
    public Course createCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }
}
