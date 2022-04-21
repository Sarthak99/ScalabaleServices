package com.example.mongoreporting.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3StorageImpl implements S3Storage {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String uploadFile(File file, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        file.delete();
        return "File uploaded:" + fileName;
    }
}
