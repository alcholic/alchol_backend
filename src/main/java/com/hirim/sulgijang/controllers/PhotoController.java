package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Photo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Value("${spring.file.url}")
    private String fileUrl;

    private final FileService fileService;

    public PhotoController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/")
    @ApiOperation("사진 저장")
    public CommonResponse uploadFile(@RequestParam("diaryId") long diaryId, @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
           return CommonResponse.success("업로드할 파일 없음");
        } else {

            String fileName = file.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();

            String destinationFileName = new StringBuilder(UUID.randomUUID().toString())
                                                    .append(".")
                                                    .append(fileNameExtension)
                                                    .toString();

            File destinationFile = new File(fileUrl + destinationFileName);

            destinationFile.getParentFile().mkdirs();
            file.transferTo(destinationFile);

            fileService.insertFile(new Photo(diaryId, destinationFileName, fileUrl));

            return CommonResponse.success();
        }
    }
}
