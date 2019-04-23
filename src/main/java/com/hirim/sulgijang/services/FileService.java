package com.hirim.sulgijang.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

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

    public Image uploadImage(MultipartFile file) throws IOException {
        ObjectMetadata fileData = new ObjectMetadata();
        fileData.setContentType(file.getContentType());
        fileData.setContentLength(file.getSize());
        fileData.setHeader("fileName", file.getOriginalFilename());

        //try {
            awsS3Client.putObject(new PutObjectRequest(bucketName, "testFile", file.getInputStream(), fileData));

        return new Image("test", awsS3Client.getUrl(bucketName, "test").toString());
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
