package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.models.response.CommonResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${spring.file.url}")
    private String fileUrl;

    @PostMapping("/upload")
    @ApiOperation(value = "이미지업로드", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();

            String destinationFileName = new StringBuilder(UUID.randomUUID().toString())
                                                    .append(".")
                                                    .append(fileNameExtension)
                                                    .toString();

            File destinationFile = new File(fileUrl + destinationFileName);

            destinationFile.getParentFile().mkdirs();
            file.transferTo(destinationFile);

            return CommonResponse.successObject(new Image(destinationFileName, fileUrl));
        } else {
            return CommonResponse.fail();
        }
    }
}
