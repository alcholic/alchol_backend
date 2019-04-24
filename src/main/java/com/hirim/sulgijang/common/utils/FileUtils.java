package com.hirim.sulgijang.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File resultFile = new File(file.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(resultFile);
        fos.write(file.getBytes());
        fos.close();

        return resultFile;
    }

    public static String generateFileName(MultipartFile file) throws IOException {
        String fileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        return new StringBuilder(UUID.randomUUID().toString())
                .append(".")
                .append(fileNameExtension)
                .toString();
    }

}
