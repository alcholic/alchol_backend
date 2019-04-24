package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
    @ApiOperation(value = "이미지업로드 여러개", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse uploadImage(@RequestHeader("file") List<MultipartFile> files) {

        System.out.println(files.toString());

        List<Image> imageList = files.stream()
                                        .map(file -> fileService.uploadFile(file))
                                        .collect(Collectors.toList());

        return CommonResponse.successObject(imageList);
    }

    @PostMapping("/upload/single")
    @ApiOperation(value = "이미지업로드 1개", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse upload(@RequestBody MultipartFile file) throws IOException {
        return CommonResponse.successObject(fileService.uploadFile(file));
    }
}
