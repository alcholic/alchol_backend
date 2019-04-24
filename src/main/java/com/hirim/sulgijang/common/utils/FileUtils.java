package com.hirim.sulgijang.common.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hirim.sulgijang.models.Image;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtils {
    private final String bucketName;
    private final AmazonS3 awsS3Client;

    public FileUtils(AmazonS3 awsS3Client, @Value("${cloud.aws.s3.bucketName}") String bucketName) {
        this.awsS3Client = awsS3Client;
        this.bucketName = bucketName;
    }

    public Image uploadFileToBucket(MultipartFile multipartFile) throws IOException {
        String fileName = generateFileName(multipartFile);
        File file = convertMultiPartToFile(multipartFile);

        awsS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));

        return new Image(fileName, awsS3Client.getUrl(bucketName, fileName).toString());
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File resultFile = new File(file.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(resultFile);
        fos.write(file.getBytes());
        fos.close();

        return resultFile;
    }

    public String generateFileName(MultipartFile file) {
        String fileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        return new StringBuilder(UUID.randomUUID().toString())
                .append(".")
                .append(fileNameExtension)
                .toString();
    }

}
