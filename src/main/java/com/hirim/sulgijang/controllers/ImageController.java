package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import com.hirim.sulgijang.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${spring.file.url}")
    private String fileUrl;

    private final FileService fileService;

    public ImageController(FileService fileService) {
        this.fileService = fileService;
    };

    @PostMapping("/upload")
    @ApiOperation(value = "이미지업로드", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse uploadImage(@RequestHeader("file") List<MultipartFile> files) {

        List<Image> imageList = files.stream().map(file -> {

            String fileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

            String destinationFileName = new StringBuilder(UUID.randomUUID().toString())
                    .append(".")
                    .append(fileNameExtension)
                    .toString();

            File destinationFile = new File(fileUrl + destinationFileName);
            destinationFile.getParentFile().mkdirs();

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new Image(destinationFileName, fileUrl);

        }).collect(Collectors.toList());

        return CommonResponse.successObject(imageList);
    }

    @PostMapping("/upload/single")
    @ApiOperation(value = "이미지업로드 1개", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse upload(@RequestBody MultipartFile file) throws IOException {
        Image image = fileService.uploadImage(file);
        return CommonResponse.successObject(image);
    }
}
