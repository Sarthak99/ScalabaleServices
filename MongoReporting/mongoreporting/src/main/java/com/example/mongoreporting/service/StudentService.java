package com.example.mongoreporting.service;

import com.example.mongoreporting.data.studentRepositories.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);
}
