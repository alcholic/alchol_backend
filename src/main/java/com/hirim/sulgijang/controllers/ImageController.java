package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import com.hirim.sulgijang.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final FileService fileService;

    public ImageController(FileService fileService) {
        this.fileService = fileService;
    };

    @PostMapping("/upload")
    @ApiOperation(value = "이미지업로드", notes = "업로드된 이미지의 파일명, url 리턴")
    public CommonResponse uploadImage(@RequestHeader("file") List<MultipartFile> files) {

        return CommonResponse.successObject(fileService.uploadFile(files));
    }
}
