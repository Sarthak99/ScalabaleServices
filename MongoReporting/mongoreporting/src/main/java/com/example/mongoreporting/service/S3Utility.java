package com.example.mongoreporting.service;

import java.io.IOException;
import java.io.InputStream;

public interface S3Utility {
    void uploadFile(String fileName, InputStream inputStream) throws IOException;
}
