package com.hirim.sulgijang.services;

import com.hirim.sulgijang.common.utils.FileUtils;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    private final FileUtils fileUtils;
    private final FileRepository fileRepository;

    public FileService(FileUtils fileUtils, FileRepository fileRepository) {
        this.fileUtils = fileUtils;
        this.fileRepository = fileRepository;
    }

    public Image uploadFile(MultipartFile file) {
        return fileUtils.uploadFileToBucket(file);
    }

    public void insertFile(Image image) {
        fileRepository.insertFile(image);
    }
}
