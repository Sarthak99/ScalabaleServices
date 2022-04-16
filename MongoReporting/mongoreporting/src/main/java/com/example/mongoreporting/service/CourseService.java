package com.example.mongoreporting.service;

import com.example.mongoreporting.data.courseRepositories.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course saveCourse(Course course);
}
