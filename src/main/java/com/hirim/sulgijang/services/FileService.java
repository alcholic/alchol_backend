package com.hirim.sulgijang.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Value("cloud.aws.s3.region")
    private static String region;

    @Value("cloud.aws.credentials.accessKey")
    private static String accessKey;

    @Value("cloud.aws.credentials.secretKey")
    private static String secretKey;

    @Value("cloud.aws.s3.bucketName")
    private static String bucketName;

    private AmazonS3 amazonS3;

    public FileService(FileRepository fileRepository) {

        this.fileRepository = fileRepository;

        Region awsRegion = Region.getRegion(Regions.fromName(region));
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(awsRegion.getName()).build();
    }

    public List<Image> uploadFile(List<MultipartFile> files) {

        List<Image> imageList = files.stream().map(file -> {

            String fileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

            String destinationFileName = new StringBuilder(UUID.randomUUID().toString())
                    .append(".")
                    .append(fileNameExtension)
                    .toString();

            File destinationFile = new File(file.getOriginalFilename());

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.amazonS3.putObject(new PutObjectRequest(bucketName, destinationFileName, destinationFile));

            return new Image(destinationFileName, "/test");

        }).collect(Collectors.toList());

        return imageList;
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
