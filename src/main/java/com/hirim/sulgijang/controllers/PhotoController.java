package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Photo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final FileService fileService;

    public PhotoController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/")
    @ApiOperation("사진 저장")
    public CommonResponse uploadFile(@RequestParam("diaryId") long diaryId, @RequestParam("file") MultipartFile files) throws IOException {

        if (files.isEmpty()) {
            // 업로드할 파일 없을시
        } else {
            String fileName = files.getOriginalFilename();
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            String fileUrl = "/Users/user/git/alchol_backend/src/main/resources/imageTesta";

            do {
                destinationFileName = "TEST" + "." + fileNameExtension;
                destinationFile = new File(fileUrl + destinationFileName);
            } while (destinationFile.exists());

            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);

            Photo photo = new Photo(diaryId, fileName, fileUrl);

            fileService.insertFile(photo);
        }
        return CommonResponse.success();
    }
}
