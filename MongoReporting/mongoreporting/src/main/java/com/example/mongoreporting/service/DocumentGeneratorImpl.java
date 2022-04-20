package com.example.mongoreporting.service;

import com.example.mongoreporting.data.courseRepositories.Course;
import com.example.mongoreporting.data.studentRepositories.Student;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class DocumentGeneratorImpl implements DocumentGenerator {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private S3Utility s3Utility;

    private void addTableHeader(PdfPTable table) {
        Stream.of("Student Name", "Course Name", "Course Description", "Instructor", "Student Updated")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    @Override
    public void generateReport() {

        List<Student> studentList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        HashMap<String, String> courseDescriptionMap = new HashMap<>();
        HashMap<String, String> courseInstructorMap = new HashMap<>();
        Document document = new Document();

        try {

            String fileName = "StudentData_"+System.currentTimeMillis()+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            studentList = studentService.getAllStudents();
            courseList = courseService.getAllCourses();

            PdfPTable table = new PdfPTable(5);

            for (Course course : courseList) {
                courseDescriptionMap.put(course.getName(), course.getDescription());
                courseInstructorMap.put(course.getName(), course.getInstructor());
            }
            document.open();
            addTableHeader(table);

            for (Student student : studentList) {
                table.addCell(student.getName());
                table.addCell(student.getCourse());
                table.addCell(courseDescriptionMap.get(student.getCourse()));
                table.addCell(courseInstructorMap.get(student.getCourse()));
                table.addCell(student.getUpdatedDate().toString());
            }
            document.add(table);
            document.close();

            File preparedFile = new File(fileName);
            InputStream targetStream = new FileInputStream(preparedFile);

            s3Utility.uploadFile(fileName,targetStream);
            log.error("File processed to S3.");

            if(preparedFile.delete()){
                log.info(fileName + " delete successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("IO Exception:" + e.getMessage());
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error("DocumentException:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception:" + e.getMessage());
        }
    }
}
