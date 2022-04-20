package com.example.mongoreporting.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class S3UtilityImpl implements S3Utility {

    @Value("${aws.bucket.name}")
    private String BUCKET;

    @Override
    public void uploadFile(String fileName, InputStream inputStream) throws IOException {

        try {
            log.info("Started S3 client build.");
            S3Client s3Client = S3Client.builder().build();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));
            log.info("S3 upload completed.");
        } catch (IOException e){
            log.error("IO Exception:" + e.getMessage());
        } catch(Exception e){
            log.error("Exception:" + e.getMessage());
        }
    }
}
