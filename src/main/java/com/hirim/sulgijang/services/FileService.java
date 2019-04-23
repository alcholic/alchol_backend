package com.hirim.sulgijang.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private AmazonS3 awsS3Client;

    public FileService(FileRepository fileRepository, AmazonS3 amazonS3Client) {
        this.fileRepository = fileRepository;
        this.awsS3Client = amazonS3Client;
    }

    public URL storeObjectInS3(MultipartFile file, String fileName, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(file.getSize());

        try {
            awsS3Client.putObject(bucketName, fileName, file.getInputStream(), objectMetadata);
        } catch(AmazonClientException | IOException exception) {
            throw new RuntimeException("Error while uploading file.");
        }

        return awsS3Client.getUrl(bucketName, fileName);
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
