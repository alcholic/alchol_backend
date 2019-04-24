package com.hirim.sulgijang.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final String bucketName;
    private final AmazonS3 awsS3Client;

    public FileService(FileRepository fileRepository, AmazonS3 amazonS3Client, @Value("${cloud.aws.s3.bucketName}") String bucketName) {
        this.fileRepository = fileRepository;
        this.awsS3Client = amazonS3Client;
        this.bucketName = bucketName;
    }

    public void uploadFile(File file, String fileName) {
        awsS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
