package com.example.mongoreporting.service;

import java.io.File;

public interface S3Storage {
    String uploadFile(File file, String fileName);
}
