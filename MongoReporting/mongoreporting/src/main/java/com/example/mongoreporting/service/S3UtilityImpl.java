package com.example.mongoreporting.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;

@Service
public class S3UtilityImpl {

    public static void uploadFile(String fileName, InputStream inputStream){

        S3Client s3Client =

    }
}
