package com.example.mongoreporting.web;

import com.example.mongoreporting.service.DocumentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportGenerationWeb {

    @Autowired
    private DocumentGenerator documentGenerator;

    @GetMapping("/studentreport")
    public void generateStudentReport(){
        documentGenerator.generateReport("123");
    }
}
