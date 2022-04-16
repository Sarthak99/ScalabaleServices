package com.example.mongoreporting.service;

import com.example.mongoreporting.data.courseRepositories.Course;
import com.example.mongoreporting.data.courseRepositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public Optional<Course> getCourseById(String id){
        return courseRepository.findById(id);
    }
}
